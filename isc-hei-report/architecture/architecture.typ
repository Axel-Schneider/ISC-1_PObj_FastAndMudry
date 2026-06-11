#import "@preview/isc-hei-report:0.8.1" : *

= Architecture du projet

Le projet est organisé autour de trois grands axes : la logique de jeu, la gestion des écrans et le rendu graphique. Cette séparation n'était pas parfaite dès le début, mais nous avons essayé de la garder le plus possible pour que le code reste lisible et qu'on puisse travailler à deux sans se marcher dessus.

Les trois schémas ci-dessous montrent le système sous trois angles complémentaires : le flux d'application, le flux de données et le flux de rendu.

Le premier schéma montre le parcours dans l'application. Le programme commence par initialiser la fenêtre principale, puis enchaîne les écrans les uns après les autres : le menu, le chargement, la partie, le garage, l'écran de fin... L'application ne se résume donc pas à un programme linéaire : elle fonctionne comme une machine à états où chaque écran représente une étape du jeu (on y revient plus en détail dans la partie dédiée).

#figure(
  image("../figs/ApplicationFlow.png", width: 50%),
  caption: [Flux d’application du projet.]
)

Le deuxième schéma se concentre sur les données. Tout tourne autour du monde du jeu : les entrées du joueur, les images, les sons et la progression alimentent un état global partagé. C'est à partir de cet état que la logique met à jour la position de la voiture, la progression du joueur, les événements du circuit et les objets interactifs. Ce qu'on voulait montrer ici, c'est que le cœur du projet n'est pas dans l'affichage, mais bien dans la gestion cohérente des informations du jeu.

#figure(
  image("../figs/DataFlow.png", width: 50%),
  caption: [Flux de données du projet.]
)

Le troisième schéma explique comment cet état est ensuite transformé en image. Le module de rendu lit les informations du monde et les passent aux différents sous-systèmes de dessin : le fond, la piste, les objets, l'interface et le HUD. Pour la piste, le rendu repose sur une projection pseudo-3D réalisée par un shader Mode 7. L'affichage n'est donc pas une simple copie de l'état du jeu, c'est plutôt une interprétation visuel de celui-ci.

#figure(
  image("../figs/RenderingFlow.png", width: 50%),
  caption: [Flux de rendu du projet.]
)

Au final, ces trois vues se complètent bien : l'application gère les états, le cœur du jeu met à jour les données, et le moteur graphique transforme ces données en image. C'est cette séparation entre logique, données et rendu qui rend le projet modulaire et plutôt facile à faire évolué.
