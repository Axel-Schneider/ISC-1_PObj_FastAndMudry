#import "@preview/isc-hei-report:0.8.1" : *

== Affichage des objets et calcul de profondeur

La même logique de projection est utilisée pour afficher les objets de jeu, tels que les obstacles ou les éléments décoratifs, au-dessus du circuit. Chaque item possède une position dans le monde, exprimée dans les coordonnées du circuit, et il est projeté dans l’espace de l’écran à partir de la position de la caméra et de son orientation. Cette transformation repose sur le calcul d’un vecteur différentiel entre la position de l’objet et la caméra, puis sur une rotation de ce vecteur selon l’angle du véhicule. Le résultat de cette opération permet de déterminer si l’objet se trouve devant ou derrière la caméra virtuelle. Si la composante de profondeur est négative ou trop faible, l’objet n’est pas projeté, ce qui évite son affichage lorsqu’il se trouve hors du champ de vision ou derrière le plan de vue.

Lorsque l’objet est visible, sa coordonnée à l’écran est obtenue par une projection perspective simple. La position horizontale est calculée à partir du rapport entre la distance latérale de l’objet et sa profondeur, tandis que la position verticale dépend à la fois de la hauteur de la caméra et de l’inclinaison du point de vue. Le facteur d’échelle est lui aussi déterminé par la profondeur selon une relation de la forme suivante.

#figure(block[
  $s = d_p / p_y$
], caption: [Échelle de l’objet calculée à partir de sa profondeur relative])

Cette relation garantit une impression cohérente de profondeur et permet de conserver une hiérarchie visuelle naturelle entre les objets proches et lointains.

Dans l’implémentation, ce calcul est centralisé dans ItemsRenderer. La méthode de projection construit un résultat composé de coordonnées d’affichage, d’un facteur d’échelle et d’une distance, puis trie les objets selon cette distance avant de les dessiner. L’ordre de rendu est ainsi déterminé par la profondeur perçue selon une priorité de traitement représentée par la relation suivante.

#figure(block[
  $x_e = w * (0.5 + (d_p * p_x) / p_y)$
], caption: [Coordonnée horizontale de projection sur l’écran])

#figure(block[
  $y_e = h * (0.5 + t - (d_p * z_c) / p_y)$
], caption: [Coordonnée verticale de projection sur l’écran])

Cette stratégie est importante, car elle évite que les éléments du décor ou les obstacles se superposent de manière incohérente et contribue à préserver la lisibilité de la scène.

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

Le flux de données peut être résumé de la manière suivante : état du véhicule → uniforms transmis par TrackRenderer → shader GPU → échantillonnage dans la texture du circuit → image finale affichée à l’écran. Pour les items, ce flux est complété par une projection supplémentaire, qui transforme leur position dans le monde en coordonnée d’écran et en échelle visuelle.
