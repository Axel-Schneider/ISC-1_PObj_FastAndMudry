#import "@preview/isc-hei-report:0.8.1" : *

== Fonctionnement du rendu Mode 7 avec le GPU

Dans l’architecture actuelle, la responsabilité de créer l’image affichée est donnée à TrackRenderer. Cet objet joue le rôle d’intermédiaire : il récupère la texture du circuit, place une caméra virtuelle selon la position du véhicule, envoie ensuite plusieurs paramètres au shader et lance le rendu. Cette séparation est pratique, car la logique du jeu reste dans les classes métier, tandis que la partie graphique se concentre sur la transformation de cette information en image.

Le composant Mode7.scala ne dessine pas directement l’écran. Il sert surtout à définir la configuration du shader : les noms des paramètres, les chemins vers le programme graphique et les valeurs par défaut. Ces paramètres sont ensuite transmis au shader par TrackRenderer. Le shader reçoit alors des informations telles que la résolution de l’écran, la position de la caméra, son angle, l’axe de rotation, la distance du plan de projection et le facteur d’échelle de la texture. Cette organisation permet de garder la projection dans un module spécialisé, indépendant de la physique de conduite ou de la gestion du jeu.

La position et l’orientation de la voiture influencent directement le rendu. À chaque image, TrackRenderer met à jour la caméra à partir des coordonnées du véhicule et de sa rotation. Ces valeurs sont envoyées au shader, qui recalculera alors l’affichage du circuit pour chaque pixel. Ainsi, quand la voiture tourne, la texture du circuit est réinterprétée sous un autre angle, ce qui donne l’illusion d’un déplacement sur une route en perspective. On peut donc voir le véhicule comme une entrée de rendu, au même titre que la taille de l’écran ou la résolution de la texture.

#figure(code()[
```scala
cameraPosition.y = World.INSTANCE.CAR.Coordinates.y
cameraPosition.x = World.INSTANCE.CAR.Coordinates.x
cameraAngle = World.INSTANCE.CAR.Rotation

g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.POSITION, cameraPosition)
g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.ANGLE, cameraAngle)
```
], caption: [Transmission des informations de déplacement au shader])

Dans le shader, la transformation part des coordonnées du fragment, c’est-à-dire des pixels à afficher à l’écran. Pour chaque pixel, le shader construit un rayon de vue dans un espace virtuel, puis applique une rotation selon l’angle de la caméra. Cette étape permet de transformer un point de l’écran en une direction de projection, puis de retrouver la position correspondante dans la texture du circuit. Le calcul combine ensuite la position de la caméra, la distance du plan de projection et le facteur de mise à l’échelle pour obtenir une coordonnée de texture comprise entre $0$ et $1$.

#figure(block[
  $r = (x_s, y_s, d) \
  r' = R(a) r \
  p = c + (r'_x, r'_y) * (c_z / r'_z) \
  t = (p - o) * f$
], caption: [Formulation générale de la projection du fragment])

Cette écriture résume le calcul effectué par le shader : un fragment, défini par ses coordonnées écran $x_s$ et $y_s$, correspond d’abord à un rayon $r$ dans un espace virtuel. Ce rayon est ensuite tourné selon l’angle $a$ de la caméra, puis il est projeté sur un plan de référence pour obtenir une position $p$ dans le repère de la carte. La coordonnée de texture $t$ est enfin obtenue en appliquant une mise à l’échelle à partir de l’origine $o$ et du facteur $f$. Lorsque cette coordonnée se trouve sur le circuit, la couleur correspondant à cette zone est lue dans la texture et affichée à l’écran. Le mécanisme est donc un problème de géométrie simple, mais appliqué à chaque pixel par le GPU.

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

Le GPU est particulièrement adapté à ce type de traitement, car il peut effectuer des calculs sur des millions de pixels en parallèle. Contrairement à un traitement séquentiel sur le CPU, le shader traite simultanément tous les pixels de l’image. Cette parallélisation est très utile ici, puisque chaque pixel est calculé à partir de la même logique de projection. Le processeur graphique prend donc en charge la transformation géométrique et la lecture de la texture, tandis que le CPU peut se concentrer sur la logique du jeu.

Le rendu repose sur une projection de la texture du circuit sur une surface virtuelle. Cette méthode est simple et efficace pour un jeu de course en 2D/2.5D, mais elle dépend beaucoup de la résolution de la texture, du réglage de la caméra et de l’alignement entre la piste et les objets décoratifs. Le shader doit aussi être réglé avec soin pour éviter les artefacts aux bords du circuit ou dans les zones où la projection devient instable. C’est un compromis adapté au projet : obtenir un rendu fluide sans ajouter une complexité de calcul trop importante.

