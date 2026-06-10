#import "@preview/isc-hei-report:0.8.1" : *

== Rendu graphique

L'un des principaux défis rencontrés durant le développement de Fast & Mudry concernait le rendu graphique de la piste. Les premières versions du projet utilisaient un affichage de type arcade reposant sur une simulation de perspective calculée directement dans le code. Cette approche permettait de représenter une route et ses virages avec un coût de développement relativement faible, mais elle présentait plusieurs limitations en termes de lisibilité et de perception de l'environnement.

#figure(image("screen/v0.0.0.png", height: 5cm), caption: [Première version du rendu]) <fig_engineer>

En pratique, les virages étaient difficiles à anticiper pour le joueur. La représentation de la piste manquait de profondeur et les changements de direction apparaissaient souvent de manière abrupte. Cette limitation avait un impact direct sur le gameplay : le joueur disposait de peu d'informations sur ce qui se trouvait au-delà de la portion immédiatement visible de la route. Il devenait alors difficile d'anticiper les obstacles, de préparer une trajectoire adaptée ou d'évaluer la géométrie du circuit à moyen terme.

Afin d'améliorer cette situation, le système de rendu a été repensé autour d'une approche de type Mode 7. Cette technique consiste à projeter une texture représentant le circuit de manière à créer une illusion de profondeur tout en conservant un environnement essentiellement bidimensionnel. Contrairement à l'ancien rendu, cette approche offre une meilleure perception de l'espace et permet d'afficher une portion beaucoup plus importante du circuit devant le véhicule.

#figure(image("screen/v1.0.0-demo-3.png", height: 5cm), caption: [Rendu avec mode 7]) <fig_engineer>

Cette évolution a considérablement amélioré la lisibilité du jeu. Le joueur peut désormais distinguer les virages à venir, observer les obstacles situés plus loin sur la piste et repérer certains éléments importants du décor, comme la HES qui matérialise la ligne d'arrivée. La visibilité accrue permet une conduite plus naturelle, fondée davantage sur l'anticipation que sur la réaction immédiate aux événements apparaissant à l'écran.
