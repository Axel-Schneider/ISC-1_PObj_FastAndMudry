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

#include "intro.typ"

#include "architecture/architecture.typ"

#include "defi/main.typ"