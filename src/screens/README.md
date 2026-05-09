# Module Screens (Gestion des Écrans)

## Responsabilité
Ce paquet gère les différents états majeurs de l'application (Menu, Chargement, Jeu en cours, Game Over). Chaque fichier représente une "scène" complète.

## Principes Clés
1.  **com.badlogic.gdx.Screen** : Toutes les classes doivent étendre cette interface.
2.  **Cycle de Vie** : Gère correctement `show()`, `render()`, `resize()`, `hide()`, et `dispose()`.
3.  **Séparation Gameplay/UI** : L'écran de jeu (`GameScreen`) ne contient pas la logique du HUD. Il instancie un `WorldRenderer` et un `HudRenderer` séparés.

## Organisation
*   **`AbstractScreen.scala`** : Classe de base commune (fortement recommandée) pour centraliser la gestion des `Viewport` et de la `SpriteBatch`.
*   **`MainMenuScreen.scala`** : L'écran d'accueil.
*   **`GameScreen.scala`** : L'écran principal du gameplay.