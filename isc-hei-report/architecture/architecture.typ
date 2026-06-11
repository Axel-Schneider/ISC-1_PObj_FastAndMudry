#import "@preview/isc-hei-report:0.8.1" : *

= Architecture du projet

Le projet suit une architecture modulaire organisée autour de trois grands axes : la logique de jeu, la gestion des écrans et le rendu graphique. Cette séparation permet de garder le code plus lisible, plus maintenable et plus facile à étendre.

Les captures ci-dessous illustrent trois vues complémentaires du système : le flux d’application, le flux de données et le flux de rendu.

Le premier schéma, consacré au flux d’application, montre que le programme démarre par l’initialisation de la fenêtre principale, puis passe par une suite d’écrans successifs. L’application ne se limite donc pas à un simple programme linéaire : elle fonctionne comme une machine à états dans laquelle chaque écran représente une étape du jeu, comme le menu, le chargement, la partie, le garage ou l’écran de fin.

#figure(
  image("../figs/ApplicationFlow.png", width: 50%),
  caption: [Flux d’application du projet.]
)

Le second schéma, centré sur le flux de données, met en évidence le rôle central du monde du jeu. Les entrées utilisateur, les assets graphiques et sonores, ainsi que les données de progression alimentent un état global partagé. À partir de cet état, la logique métier met à jour la position de la voiture, la progression du joueur, les événements du circuit et les éléments interactifs. Cette vue montre donc que le cœur du projet n’est pas dans l’affichage, mais dans la gestion cohérente des informations du jeu.

#figure(
  image("../figs/DataFlow.png", width: 50%),
  caption: [Flux de données du projet.]
)

Le troisième schéma, consacré au flux de rendu, explique comment cet état est ensuite transformé en image. Le module de rendu lit les informations du monde, puis les transmet aux différents sous-systèmes de dessin : fond, piste, objets, interface et HUD. Pour la piste, le rendu repose sur une projection pseudo-3D réalisée par un shader Mode 7. Cette étape montre que l’affichage n’est pas une simple copie de l’état du jeu, mais une interprétation visuelle de celui-ci.

#figure(
  image("../figs/RenderingFlow.png", width: 50%),
  caption: [Flux de rendu du projet.]
)

Dans l’ensemble, ces trois vues sont cohérentes entre elles. L’application gère les états, le cœur du jeu met à jour les données, et le moteur graphique transforme ces données en image. C’est cette séparation entre logique, données et rendu qui donne au projet son architecture modulaire et compréhensible.
