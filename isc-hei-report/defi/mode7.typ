#import "@preview/isc-hei-report:0.8.1" : *

== Fonctionnement du rendu Mode 7 avec le GPU

Dans l’architecture actuelle, la responsabilité de définir la scène visuelle est déléguée à TrackRenderer. Cet objet agit comme un orchestrateur de rendu : il récupère la texture du circuit depuis le monde, positionne la caméra virtuelle à partir de l’état du véhicule, transmet ensuite un ensemble de paramètres au shader graphique et déclenche l’affichage. Cette séparation des responsabilités est cohérente avec un design modulaire, car la logique de jeu continue d’évoluer dans les classes métier, tandis que le rendu se limite à la transformation de l’état du monde en primitives graphiques exploitables par le GPU.

Le composant Mode7.scala ne calcule pas lui-même l’image finale. Il sert principalement de conteneur de configuration, en exposant les chemins du shader, les noms des uniforms et les valeurs par défaut utilisées par le moteur de rendu. Les uniforms sont ensuite envoyés depuis TrackRenderer vers le shader via le renderer graphique. Le shader, quant à lui, reçoit le contexte de rendu sous forme de variables globales, dont la résolution, la position de la caméra, son angle, l’axe de rotation, la distance du plan d’écran, l’inclinaison du point de vue et les facteurs de mise à l’échelle associés à la texture. Cette organisation permet de garder la logique de projection dans un composant spécialisé, indépendamment de la physique de conduite ou de la gestion de l’état du jeu. La caméra virtuelle est ainsi représentée par un ensemble de paramètres de projection et de position, ce qui rend le système facilement configurable et modulable.

La position et l’orientation de la voiture influencent directement le rendu. À chaque image, TrackRenderer met à jour la position de la caméra à partir des coordonnées du véhicule et son angle à partir de la rotation de la voiture. Ces valeurs sont transférées au shader sous forme d’uniforms, ce qui permet au GPU de recalculer la projection du circuit pour chaque pixel. Ainsi, lorsqu le véhicule tourne, la texture du circuit est réinterprétée selon une nouvelle direction de regard, ce qui donne l’illusion d’un déplacement sur une route en perspective. Le système repose donc sur l’idée que l’état du véhicule est une entrée de rendu, au même titre que les dimensions de l’écran ou la résolution de la texture.

#figure(code()[
```scala
cameraPosition.y = World.INSTANCE.CAR.Coordinates.y
cameraPosition.x = World.INSTANCE.CAR.Coordinates.x
cameraAngle = World.INSTANCE.CAR.Rotation

g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.POSITION, cameraPosition)
g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.ANGLE, cameraAngle)
```
], caption: [Transmission des informations de déplacement au shader])

Dans le shader, la transformation s’effectue à partir des coordonnées du fragment, c’est-à-dire des pixels de l’écran. Pour chaque pixel, le shader construit un rayon de vue dans un espace 3D synthétique, puis applique une rotation autour d’un axe défini par la caméra. Cette opération permet de transformer un point de l’écran en une direction de projection, puis d’inverser cette projection pour retrouver une position dans la texture du circuit. Le calcul combine ensuite la position de la caméra, la distance du plan d’écran et le facteur de rendu pour obtenir une coordonnée de texture comprise entre $0$ et $1$. Lorsque cette coordonnée appartient à la zone du circuit, la couleur correspondante est échantillonnée dans la texture et affichée à l’écran. Le mécanisme est donc fondamentalement un problème de géométrie projective, résolu en parallèle par le GPU pour chaque pixel.

#figure(code()[
```glsl
vec3 pixelRay;
pixelRay.xz = (gl_FragCoord.xy / resolution.xy) * vec2(1.0, -1.0) + vec2(-0.5, 0.5);
pixelRay.y = screenPlanDistance;
pixelRay *= rotationMatrix(cameraAxis, cameraAngle);

vec2 hitPosition = cameraPosition.xy + pixelRay.xy * (cameraPosition.z / pixelRay.z) + vec2(0.5, 0);
hitPosition = (hitPosition - mapOrigin) * renderingFactor;
gl_FragColor = texture2D(backbuffer, hitPosition);
```
], caption: [Projection du fragment vers une coordonnée de texture])

Le GPU est particulièrement adapté à ce type de traitement car il exécute des opérations de calcul sur des millions de fragments de manière massivement parallèle. Contrairement à un calcul séquentiel dans le code CPU, le shader traite simultanément l’ensemble des pixels qui composent l’image. Cette parallélisation est particulièrement pertinente pour un rendu Mode 7, puisque chaque pixel doit être évalué indépendamment à partir de la même logique de projection. L’architecture adoptée est ainsi conforme aux principes de calcul graphique moderne, où le traitement géométrique et texturisé est délégué au processeur graphique afin de libérer le CPU pour la logique du jeu.

Le rendu repose sur une projection de la texture du circuit sur une surface virtuelle. Cette représentation reste simple et efficace pour un jeu de course 2D/2.5D, mais elle dépend fortement de la résolution de la texture, du réglage des paramètres de caméra et de l’alignement géométrique entre la piste et ses éléments décoratifs. Le shader doit également être paramétré avec précision afin d’éviter les artefacts aux limites du circuit ou dans les zones où la projection devient instable. Ce compromis est cohérent avec les objectifs du projet : offrir un rendu fluide et lisible sans introduire une complexité de calcul disproportionnée.


Le flux de données peut être résumé de la manière suivante : état du véhicule → uniforms transmis par TrackRenderer → shader GPU → échantillonnage dans la texture du circuit → image finale affichée à l’écran.
