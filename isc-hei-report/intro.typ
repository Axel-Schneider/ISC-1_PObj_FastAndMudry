= Introduction

Dans le cadre du projet de programmation orientée objet, nous avons développé Fast & Mudry, un jeu de course réalisé en Scala avec la bibliothèque LibGDX. Le joueur contrôle un véhicule évoluant sur un circuit généré dynamiquement, tout en devant éviter différents obstacles et gérer les contraintes liées à la conduite.

Au cours du développement, plusieurs choix techniques ont été réalisés afin d'améliorer l'organisation du code et le rendu visuel du jeu. L'une des évolutions les plus importantes a notamment été le remplacement d'un système de rendu basé sur une perspective calculée directement dans le code par une approche reposant sur le Mode 7 et l'utilisation de shaders graphiques. Cette évolution a conduit à une réorganisation de certains composants du moteur de rendu tout en conservant la logique métier du jeu.

Ce rapport présente l'architecture logicielle du projet, les principaux mécanismes mis en œuvre ainsi que les choix de conception retenus. Une attention particulière est portée à l'organisation des différents modules, aux interactions entre les composants et à l'application des concepts de programmation orientée objet.
