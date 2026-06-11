#import "@preview/isc-hei-report:0.8.1" : *

== Rendu graphique

Un des plus gros défis du projet a été le rendu de la piste. Les premières versions du jeu utilisaient un affichage de type arcade, avec une perspective simulée directement dans le code. C'était relativement rapide à mettre en place et ça donnait déjà une impression de route avec des virages, mais on a vite vu les limites de cette approche.

#figure(image("screen/v0.0.0.png", height: 5cm), caption: [Première version du rendu]) <fig_engineer>

En pratique, les virages étaient presque impossible à anticiper. La route manquait de profondeur et les changements de direction arrivaient d'un coup, sans prévenir. Pour le joueur c'était frustrant : on ne voyait que la portion de route juste devant la voiture, donc difficile de préparer une trajectoire ou d'éviter un obstacle qu'on découvre au dernier moment.

Pour corriger ça, nous avons repensé le système de rendu autour d'une approche de type Mode 7, la technique utilisée à l'époque par des jeux comme Mario Kart sur Super Nintendo. L'idée est de projeter une texture représentant le circuit de façon à créer une illusion de profondeur, tout en restant dans un environnement essentiellement 2D. Par rapport à l'ancien rendu, on voit une portion beaucoup plus grande du circuit devant le véhicule.

#figure(image("screen/v1.0.0-demo-3.png", height: 5cm), caption: [Rendu avec mode 7]) <fig_engineer>

Ce changement a vraiment amélioré la lisibilité du jeu. Le joueur distingue maintenant les virages à venir, repère les obstacles situés plus loin sur la piste et peut voir certains éléments important du décor, comme la HES qui marque la ligne d'arrivée. La conduite devient plus naturelle : on anticipe au lieu de simplement réagir à ce qui apparait à l'écran.
