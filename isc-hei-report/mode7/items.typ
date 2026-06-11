#import "@preview/isc-hei-report:0.8.1" : *

== Affichage des objets et calcul de profondeur

La même logique de projection est réutilisée pour afficher les objets du jeu, comme les obstacles ou les éléments du décor, par-dessus le circuit. Chaque item possède une position dans le monde, exprimée dans les coordonnées du circuit, et il faut le projeter dans l'espace de l'écran en fonction de la position de la caméra et de son orientation. Pour ça, on calcule d'abord un vecteur entre la position de l'objet et celle de la caméra, puis on fait tourner ce vecteur selon l'angle du véhicule. Le résultat permet de savoir si l'objet se trouve devant ou derrière la caméra virtuelle : si la composante de profondeur est négative ou trop petite, on ne le projete pas du tout, ce qui évite d'afficher des objets qui sont derrière nous ou hors du champ de vision.

Quand l'objet est visible, sa position à l'écran est obtenue par une projection perspective simple. La position horizontale vient du rapport entre la distance latérale de l'objet et sa profondeur, et la position verticale dépend à la fois de la hauteur de la caméra et de l'inclinaison du point de vue. L'échelle de l'objet est elle aussi calculée à partir de la profondeur, avec une relation de la forme suivante.

#figure(block[
  $s = d_p / p_y$
], caption: [Échelle de l’objet calculée à partir de sa profondeur relative])

Grâce à cette relation, les objets proches paraissent gros et les objets lointain tout petits, ce qui donne une impression de profondeur cohérente entre tous les éléments de la scène.

Dans le code, tout ce calcul est centralisé dans ItemsRenderer. La méthode de projection retourne les coordonnées d'affichage, un facteur d'échelle et une distance, puis les objets sont triés selon cette distance avant d'être dessinés. Les coordonnées à l'écran sont obtenues avec les deux relations suivantes.

#figure(block[
  $x_e = w * (0.5 + (d_p * p_x) / p_y)$
], caption: [Coordonnée horizontale de projection sur l’écran])

#figure(block[
  $y_e = h * (0.5 + t - (d_p * z_c) / p_y)$
], caption: [Coordonnée verticale de projection sur l’écran])

Ce tri par distance est important : sans lui, des éléments du décor ou des obstacles pouvaient se superposer n'importe comment et la scène devenait illisible.

#figure(code()[
```scala
val differentialVector = new Vector2(position.x, position.y)
differentialVector.sub(cameraPosition.x + 0.5f, cameraPosition.y)

val primeVector = new Vector2(
  differentialVector.x * cosAlpha - differentialVector.y * sinAlpha,
  differentialVector.x * sinAlpha + differentialVector.y * cosAlpha
)

if (primeVector.y <= 0.01f) return None

val screenX = screenW * (0.5f + (screenPlanDistance * primeVector.x) / primeVector.y)
val screenY = screenH * (0.5f + pitch - (screenPlanDistance * cameraPosition.z) / primeVector.y)

val scale = screenPlanDistance / primeVector.y
```
], caption: [Projection d’un item dans l’espace de l’écran])

Pour résumé le flux de données : l'état du véhicule est transmis sous forme d'uniforms par TrackRenderer, le shader GPU échantillonne la texture du circuit, et l'image finale s'affiche à l'écran. Pour les items, il y a juste une projection en plus, qui transforme leur position dans le monde en coordonnée d'écran et en échelle visuelle.
