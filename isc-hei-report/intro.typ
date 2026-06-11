= Introduction

Dans le cadre du projet de programmation orientée objet, nous avons développé Fast & Mudry, un jeu de course en 2D écrit en Scala avec la bibliothèque gdx2d. Le principe est simple : le joueur contrôle une voiture qui doit traverser un circuit généré aléatoirement, en évitant les obstacles et en faisant attention à ne pas casser son véhicule en chemin.

Le développement ne s'est pas fait en ligne droite. La plus grosse évolution du projet a été le changement complet du système de rendu : les premières versions utilisaient une perspective calculée à la main dans le code, et nous l'avons remplacé par un rendu de type Mode 7 basé sur un shader qui tourne sur le GPU. Ce changement nous a obligé à réorganiser une partie du moteur de rendu, mais la logique du jeu elle-même a pu être conservé presque telle quelle.

Ce rapport présente d'abord l'architecture générale du projet, puis la génération de la route et de la carte, la machine à états qui gère le déroulement du jeu, et enfin les défis qu'on a rencontré sur le rendu graphique, en particulier le passage au Mode 7 et l'affichage des objets.
