# Module UI (Interface Utilisateur & HUD)

## Responsabilité
Ce paquet gère tous les éléments graphiques superposés au monde du jeu (Heads-Up Display - HUD) comme le score, la santé, ou les menus de pause, ainsi que les widgets d'interface purs.

## Principes Clés
1.  **Séparation du Monde** : L'UI utilise souvent sa propre caméra et sa propre `SpriteBatch` (souvent via `Scene2D`) pour rester fixe par rapport à l'écran.
2.  **Scene2D (Optionnel)** : Fortement recommandé pour les menus complexes.
3.  **Read-Only Core** : Lit l'état du `core` pour mettre à jour les éléments visuels (ex: santé du joueur).

## Organisation
*   **`HudRenderer.scala`** : Dessine l'interface superposée au jeu.
*   **`components/`** : Contient des composants UI réutilisables (boutons, barres de progression personnalisées).