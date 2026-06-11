#import "@preview/isc-hei-report:0.8.1" : *

= Conclusion

Ce projet nous a permis de construire un jeu complet, de l'écran de menu jusqu'à la cinématique finale, en passant par la génération procédurale de la carte, une machine à états, un rendu pseudo-3D sur le GPU et la gestion de la musique et des sons. Au départ, l'objectif était simplement de faire rouler une voiture sur une route ; au final, on se retrouve avec trois biomes, un garage, un quiz et des pneus qui explosent. Au-delà du jeu lui-même, le projet nous a apporté beaucoup de connaissance sur la programmation orientée objet.

Sur le plan technique, la plus grande leçon du projet est probablement de ne pas avoir peur de jeter du code. Nous avions passé pas mal de temps sur le premier système de rendu, et le remplacer entièrement par le Mode 7 n'était pas une décision facile. Mais c'est ce changement qui a rendu le jeu réellement agréable à jouer.

Le travail à deux s'est bien passé, notamment grâce au découpage du code en modules et à l'utilisation de branches git avec des pull requests. Chacun pouvait avancer sur sa partie sans bloquer l'autre, et les conflits de merge sont restés rares.

Évidemment, tout n'est pas parfait. Certaines parties aurait mérité un peu plus de polissage, comme les réglages du shader qui restent un peu fragile au bord du circuit. Et si le projet devait continuer, on aimerait ajouter plus de journées avec de nouveaux biomes, enrichir la physique des collisions et pourquoi pas un mode deux joueurs. Mais dans l'ensemble, nous sommes plutôt fier du résultat : un jeu qui se lance, qui se joue, et qui donne envie de refaire une partie pour voir un circuit différent.
