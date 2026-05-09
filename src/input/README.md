# Module Input (Gestion des Entrées)

## Responsabilité
Ce paquet capture et interprète les entrées de l'utilisateur (clavier, souris, manette) pour les traduire en actions de jeu.

## Principes Clés
1.  **Séparation de la Logique** : Ce module ne doit pas modifier l'état du jeu directement. Il doit lever des drapeaux (flags) ou appeler des méthodes de haut niveau dans le `core`.
2.  **InputProcessor** : Implémentez `com.badlogic.gdx.InputProcessor`.
3.  **Multiplexing** : Si nécessaire, utilisez `InputMultiplexer` pour gérer les entrées à la fois pour l'interface (UI) et le gameplay.

## Classe Principale
*   **`GameInputHandler.scala`** : Reçoit les événements bruts et les convertit en commandes (ex: `isMovingLeft = true`).