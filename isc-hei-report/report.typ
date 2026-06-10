//        ___ ____   ____      _   _ _____ ___
//       |_ _/ ___| / ___|    | | | | ____|_ _|     Informatique et
//        | |\___ \| |   ___  | |_| |  _|  | |       systèmes de communication
//        | | ___) | |__|___| |  _  | |___ | |       HEI Sion · HES-SO Valais / mui 24-26
//       |___|____/ \____|    |_| |_|_____|___|
//
//   52 65 61 64 69 6e 67 20 68 65 78 20 66 6f 72 20 66 75 6e 3f 20 49 53 43 20 66 6f 72 65 76 65 72
// 
#import "@preview/isc-hei-report:0.8.1" : *

#let doc_language = "fr" // The document language, valid values are [en, fr]

#show: project.with(
  title: "Rapport Fast & Mudry",
  subtitle: [Projet de POO],
  authors: ("Axel Schneider", "Helder Ribeiro"),  
  date: datetime.today(), 
    
  course-name: "101.2 Programmation orientée objet",
  course-supervisor: "Prof. Dr P.-A. Mudry",
  semester: "Semestre de printemps",
  academic-year: "2025-2026",
  
  logo: image("figs/isc_logo.svg"),
  cover-image: image("figs/FastAndMudry_logo.png"), // Change this to your cover image
  cover-image-height: 8cm,
  cover-image-caption: [Logo Fast and Mudry - AI generated],
    
  show-toc: true, // Set to true if you want a table of contents, or 1, 2, 3... for a specific depth
  language: doc_language, // Please change the value above if required
  code-theme: "bluloco-light", // See directory themes/ for available themes
)

// If using acronyms
#import "@preview/acrostiche:0.7.0": *
#include "acronyms.typ"

// A tidy acronym table for the appendix, using the acronyms declared above.
#let acronym-table() = print-index(
  title: page-title(i18n(doc_language, "acronym-table-title"), mult: 1, top: 1em, bottom: 1em),
  sorted: "up",
  delimiter: " : ",
  row-gutter: 0.7em,
  outlined: false,
)

// Let's get started folks!

_*Note* : Le rapport a été générée avec l’assistance d’une intelligence artificielle. Le contenu a été revu et validé par l’auteur avant son intégration au document final._

= Introduction

Dans le cadre du projet de programmation orientée objet, nous avons développé Fast & Mudry, un jeu de course réalisé en Scala avec la bibliothèque LibGDX. Le joueur contrôle un véhicule évoluant sur un circuit généré dynamiquement, tout en devant éviter différents obstacles et gérer les contraintes liées à la conduite.

Au cours du développement, plusieurs choix techniques ont été réalisés afin d'améliorer l'organisation du code et le rendu visuel du jeu. L'une des évolutions les plus importantes a notamment été le remplacement d'un système de rendu basé sur une perspective calculée directement dans le code par une approche reposant sur le Mode 7 et l'utilisation de shaders graphiques. Cette évolution a conduit à une réorganisation de certains composants du moteur de rendu tout en conservant la logique métier du jeu.

Ce rapport présente l'architecture logicielle du projet, les principaux mécanismes mis en œuvre ainsi que les choix de conception retenus. Une attention particulière est portée à l'organisation des différents modules, aux interactions entre les composants et à l'application des concepts de programmation orientée objet.

== Défi et problèmatique

=== Rendu graphique

L'un des principaux défis rencontrés durant le développement de Fast & Mudry concernait le rendu graphique de la piste. Les premières versions du projet utilisaient un affichage de type arcade reposant sur une simulation de perspective calculée directement dans le code. Cette approche permettait de représenter une route et ses virages avec un coût de développement relativement faible, mais elle présentait plusieurs limitations en termes de lisibilité et de perception de l'environnement.



En pratique, les virages étaient difficiles à anticiper pour le joueur. La représentation de la piste manquait de profondeur et les changements de direction apparaissaient souvent de manière abrupte. Cette limitation avait un impact direct sur le gameplay : le joueur disposait de peu d'informations sur ce qui se trouvait au-delà de la portion immédiatement visible de la route. Il devenait alors difficile d'anticiper les obstacles, de préparer une trajectoire adaptée ou d'évaluer la géométrie du circuit à moyen terme.

Afin d'améliorer cette situation, le système de rendu a été repensé autour d'une approche de type Mode 7. Cette technique consiste à projeter une texture représentant le circuit de manière à créer une illusion de profondeur tout en conservant un environnement essentiellement bidimensionnel. Contrairement à l'ancien rendu, cette approche offre une meilleure perception de l'espace et permet d'afficher une portion beaucoup plus importante du circuit devant le véhicule.

Cette évolution a considérablement amélioré la lisibilité du jeu. Le joueur peut désormais distinguer les virages à venir, observer les obstacles situés plus loin sur la piste et repérer certains éléments importants du décor, comme la HES qui matérialise la ligne d'arrivée. La visibilité accrue permet une conduite plus naturelle, fondée davantage sur l'anticipation que sur la réaction immédiate aux événements apparaissant à l'écran.


