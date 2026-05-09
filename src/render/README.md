# Module Render (Rendu Graphique du Monde)

## Responsabilité
Ce paquet est chargé de dessiner à l'écran l'état actuel du monde du jeu défini dans le module `core`. Il gère les caméras, les batches, et les effets visuels.

## Principes Clés
1.  **Read-Only Core** : Ce module lit l'état du `core` mais ne le modifie jamais.
2.  **Optimisation** : Utilisez `SpriteBatch` de manière efficace (regroupez les dessins).
3.  **Coordonnées** : Gérez la conversion entre les coordonnées du monde (Gameplay) et les coordonnées de l'écran.

## Organisation
*   **`WorldRenderer.scala`** : La classe principale qui orchestre le dessin du monde.
*   **`effects/`** : Contient la gestion des particules, des shaders et des effets post-process.