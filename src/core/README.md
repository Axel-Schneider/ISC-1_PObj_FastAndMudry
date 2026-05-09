# Module Core (Logique Pure du Jeu)

## Responsabilité
Ce paquet contient le cœur de la logique du gameplay. Il définit le monde du jeu, les entités qui le composent, leur état, et les règles d'interaction entre elles. Ce module est idéalement indépendant de la manière dont le jeu est dessiné.

## Organisation
Ce dossier est structuré pour supporter la complexité croissante du gameplay.

### Sous-dossiers Proposés
*   **`ecs/` (Entity Component System)**
    *   `components/` : Données pures attachées aux entités.
    *   `systems/` : Logique pure traitant les composants.
    *   `entities/` : Factories pour créer des entités.
*   **`world/`** : Gère l'état global du monde, la carte, et éventuellement la physique (Box2D).

## Interaction
Le `core` met à jour l'état du jeu à chaque frame. Le module `render` utilise cet état pour savoir quoi dessiner.