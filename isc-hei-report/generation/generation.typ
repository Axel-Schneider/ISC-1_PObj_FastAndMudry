#import "@preview/isc-hei-report:0.8.1" : *

= Génération de la route et de la carte

Un des objectifs du projet était que chaque partie soit un peu différente. Plutôt que de dessiner les circuits à la main, nous avons choisi de générer la route de manière procédurale au début de chaque journée de jeu. À chaque chargement, le joueur découvre donc un circuit qu'il n'a jamais vu.

== La ligne centrale

Tout part de la classe TrackGeometry, qui construit la ligne centrale de la route. Elle reçoit un point de départ, un point d'arrivée et un nombre de points de contrôle (20 dans notre cas). Les points sont d'abord répartis régulièrement entre le départ et l'arrivée, puis chacun reçoit un décalage aléatoire vertical et horizontal, sauf le premier et le dernier qui restent fixes pour garder un départ et une arrivée propre. À ce stade, la route ressemble à une suite de segments anguleux, ce qui n'est pas très agréable à conduire. Les points sont donc ensuite lissé avec une spline de Catmull-Rom, qui interpole 30 sous-points entre chaque paire de points de contrôle. Le résultat est une courbe fluide avec des virages naturels.

#figure(code()[
```scala
val yNoise = ((Math.random - 0.5) * 2.0 * randomHeight).toFloat
val h: Float = start.y + height * i + yNoise
val xNoise = if (i == 0 || i == nPoints - 1) 0f
             else ((Math.random - 0.5) * 2.0 * width * 0.4).toFloat
val p: Vector2 = new Vector2(start.x + width * i + xNoise, h)
```
], caption: [Génération des points de contrôle aléatoires de la route])

Cette ligne centrale sert ensuite de référence pour tout le reste. La géométrie expose une méthode qui calcule la distance entre un point quelconque et la ligne, ce qui permet de savoir si la voiture est sur la route ou non. Comme ce calcul est appelé énormément de fois, chaque segment possède une boîte englobante qui permet d'ignorer rapidement les segments trop éloignés. La ligne d'arrivée est simplement définie comme un cercle autour du dernier point de la courbe.

== De la géométrie à l'image

La carte affichée à l'écran est en réalité une grande texture générée par TrackTexture. L'objet parcourt chaque pixel de l'image et calcule sa distance a la ligne centrale. En fonction de cette distance, le pixel devient soit la ligne du milieu de la route, soit la route elle-même, soit le bas-côté, soit le terrain hors-piste. Les pixels proches du point d'arrivée sont dessinés en noir pour matérialiser la ligne d'arrivée.

Pour éviter d'avoir des surfaces complètement uniformes, les couleurs ne sont pas fixes. Chaque biome fournit une couleur claire et une couleur foncée pour chaque type de surface, et un bruit de Perlin vient mélanger les deux. Ça donne des textures avec des couleurs un peu différente d'un pixel à l'autre, ce qui rend le sol beaucoup plus vivant. Le même principe est utilisé pour faire varier légèrement la largeur du bas-côté, comme ça le bord de la route n'est pas une ligne parfaite.

== Les biomes et les objets

Chaque journée du jeu possède son propre biome : la forêt, le désert et la neige. Le biome définit les couleurs de la carte, la musique, le décor en arrière-plan et aussi la physique quand on sort de la route. C'est également lui qui place les objets sur la carte, comme les arbres, les rochers ou les cactus. Pour chaque objet, une position est tirée aléatoirement sur la carte, et on recommence le tirage tant que la position ne convient pas, par exemple pour éviter qu'un arbre se retrouve au milieu de la route. Le bâtiment de la HES, lui, est toujours posé exactement sur le point d'arrivée.

Comme la génération de la texture est assez lourde (chaque pixel demande un calcul de distance), elle est lancée dans un thread séparé pendant que l'écran de chargement affiche une petite animation. Ça nous a permis d'éviter de bloqué la fenêtre du jeu pendant la génération. Une fois le calcul terminé, la texture est envoyée au GPU, la voiture est placée sur le premier point de la ligne centrale et orientée vers le deuxième, et la partie peut commencer.
