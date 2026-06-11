#import "@preview/isc-hei-report:0.8.1" : *

== Fonctionnement du rendu Mode 7 avec le GPU

Dans l'architecture actuelle, c'est TrackRenderer qui est responsable de créer l'image affichée. Il joue un rôle d'intermédiaire : il récupère la texture du circuit, place une caméra virtuelle en fonction de la position du véhicule, envoie une série de paramètres au shader et lance le rendu. Cette séparation est pratique car la logique du jeu reste dans les classes métier, pendant que la partie graphique s'occupe juste de transformer ces informations en image.

Le fichier Mode7.scala ne dessine rien lui-même. Il sert surtout de configuration pour le shader : les noms des paramètres, les chemins vers le programme graphique et les valeurs par défaut. TrackRenderer transmet ensuite tout ça au shader : la résolution de l'écran, la position de la caméra, son angle, l'axe de rotation, la distance du plan de projection et le facteur d'échelle de la texture. L'avantage, c'est que la projection reste dans un module spécialisé, complètement indépendant de la physique de conduite ou de la gestion du jeu.

La position et l'orientation de la voiture influencent directement le rendu. À chaque image, TrackRenderer met à jour la caméra à partir des coordonnées du véhicule et de sa rotation, puis envoie ces valeurs au shader qui recalcule l'affichage du circuit pour chaque pixel. Du coup, quand la voiture tourne, la texture du circuit est réinterprété sous un autre angle, ce qui donne l'illusion qu'on se déplace sur une vraie route en perspective. On peut donc voir le véhicule comme une entrée du rendu, au même titre que la taille de l'écran ou la résolution de la texture.

#figure(code()[
```scala
cameraPosition.y = World.INSTANCE.CAR.Coordinates.y
cameraPosition.x = World.INSTANCE.CAR.Coordinates.x
cameraAngle = World.INSTANCE.CAR.Rotation

g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.POSITION, cameraPosition)
g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.ANGLE, cameraAngle)
```
], caption: [Transmission des informations de déplacement au shader])

Côté shader, le calcul part des coordonnées du fragment, c'est à dire du pixel à afficher à l'écran. Pour chaque pixel, le shader construit un rayon de vue dans un espace virtuel, applique une rotation selon l'angle de la caméra, puis retrouve la position correspondante dans la texture du circuit. En combinant la position de la caméra, la distance du plan de projection et le facteur de mise à l'échelle, on obtient au final une coordonnée de texture comprise entre $0$ et $1$.

#figure(block[
  $r = (x_s, y_s, d) \
  r' = R(a) r \
  p = c + (r'_x, r'_y) * (c_z / r'_z) \
  t = (p - o) * f$
], caption: [Formulation générale de la projection du fragment])

Cette écriture résume le calcul fait par le shader : un fragment, défini par ses coordonnées écran $x_s$ et $y_s$, correspond d'abord à un rayon $r$ dans un espace virtuel. Ce rayon est tourné selon l'angle $a$ de la caméra, puis projeté sur un plan de référence pour obtenir une position $p$ dans le repère de la carte. La coordonnée de texture $t$ est enfin obtenue en appliquant une mise à l'échelle à partir de l'origine $o$ et du facteur $f$. Si cette coordonnée tombe sur le circuit, la couleur de cette zone est lue dans la texture et affichée à l'écran. Au final c'est de la géométrie assez simple, mais appliqué à chaque pixel par le GPU.

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

Le GPU est parfait pour ce genre de traitement, parce qu'il peut faire les calculs sur des millions de pixels en parallèle. Contrairement au CPU qui traiterait les pixels un par un, le shader les traite tous en même temps avec la même logique de projection. Le processeur graphique s'occupe donc de la transformation géométrique et de la lecture de la texture, ce qui laisse le CPU se concentrer sur la logique du jeu.

Cette méthode est simple et efficace pour un jeu de course en 2D/2.5D, mais elle a ses limites : tout dépend de la résolution de la texture, du réglage de la caméra et de l'alignement entre la piste et les objets du décor. Il a aussi fallu réglé le shader avec soin pour éviter les artefacts au bord du circuit, là où la projection devient instable. C'est un compromis qui nous convenait bien : un rendu fluide sans rajouter une complexité de calcul trop importante.
