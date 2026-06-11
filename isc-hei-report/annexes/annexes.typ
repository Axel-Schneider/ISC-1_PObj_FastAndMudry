#import "@preview/isc-hei-report:0.8.1" : *

= Annexes

== Liens et références

Voici les différentes ressources qui nous ont aidé pendant le projet. La plupart sont aussi citées en commentaire directement dans le code, aux endroits concernés.

- #link("https://www.youtube.com/watch?v=ybLZyY655iY")[Programming Pseudo 3D Planes aka MODE7 (javidx9)] : la vidéo qui nous a servi de référence pour comprendre le fonctionnement du rendu Mode 7.
- #link("https://www.youtube.com/watch?v=KkMZI5Jbf18")[Code-It-Yourself! Retro Arcade Racing Game (javidx9)] : l'inspiration principale de la toute première version du rendu, de type arcade.
- #link("https://www.youtube.com/watch?v=N60lBZDEwJ8")[Let's make 16 games in C++ : Outrun (FamTrinli)] : un autre exemple de jeu de course en pseudo-3D qui nous a inspiré au début du projet.
- #link("https://www.youtube.com/watch?v=9_aJGUTePYo")[Programming & Using Splines - Part\#1 (javidx9)] : une vidéo sur les splines, utile pour comprendre le lissage de la ligne centrale de la route.
- #link("https://github.com/yatsukha/perlin-noise")[perlin-noise (GitHub)] : le dépôt d'où vient la majorité du code de génération de bruit de Perlin, utilisé pour les textures des biomes.
- #link("https://www.scala-algorithms.com/StateMachine/")[Scala Algorithms : StateMachine] : la structure sur laquelle est basé notre machine à états.
- #link("https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment")[Stack Overflow : distance point-segment] : l'algorithme de calcul de distance entre un point et un segment, utilisé pour déterminer si la voiture est sur la route.
- #link("https://libgdx.com/wiki/graphics/2d/scene2d/scene2d")[Documentation LibGDX : Scene2D] : la documentation utilisée pour la construction de l'interface utilisateur.

== Code source du jeu

Cette annexe contient l'intégralité du code source Scala du jeu, ainsi que le shader GLSL utilisé pour le rendu Mode 7.

#let source-files = (
  "../../src/core/audio/AudioManager.scala",
  "../../src/core/audio/MusicTrack.scala",
  "../../src/core/audio/VolumeMusicPlayer.scala",
  "../../src/core/car/CarSkin.scala",
  "../../src/core/ecs/components/AGameLoop.scala",
  "../../src/core/ecs/components/Axle.scala",
  "../../src/core/ecs/components/Collision/CircleCollision.scala",
  "../../src/core/ecs/components/Collision/Collisional.scala",
  "../../src/core/ecs/components/Collision/DefectableCollisional.scala",
  "../../src/core/ecs/components/Collision/DefectChassis.scala",
  "../../src/core/ecs/components/Collision/NoDefect.scala",
  "../../src/core/ecs/components/Collision/PassThoughtCollision.scala",
  "../../src/core/ecs/components/GodMode.scala",
  "../../src/core/ecs/components/HasTires.scala",
  "../../src/core/ecs/components/Locatable.scala",
  "../../src/core/ecs/components/Moveable.scala",
  "../../src/core/ecs/components/Orientable.scala",
  "../../src/core/ecs/components/problems/Critical.scala",
  "../../src/core/ecs/components/problems/Problem.scala",
  "../../src/core/ecs/components/problems/Reparable.scala",
  "../../src/core/ecs/components/Side.scala",
  "../../src/core/ecs/components/Steerable.scala",
  "../../src/core/ecs/components/Temperable.scala",
  "../../src/core/ecs/entities/Item/AItem.scala",
  "../../src/core/ecs/entities/Item/desert/Cactus.scala",
  "../../src/core/ecs/entities/Item/desert/DeserticRock.scala",
  "../../src/core/ecs/entities/Item/desert/DeserticSkeleton.scala",
  "../../src/core/ecs/entities/Item/forest/SimpleBush.scala",
  "../../src/core/ecs/entities/Item/forest/SimpleRock.scala",
  "../../src/core/ecs/entities/Item/forest/SimpleTree.scala",
  "../../src/core/ecs/entities/Item/HES.scala",
  "../../src/core/ecs/entities/Item/ItemTextures.scala",
  "../../src/core/ecs/entities/Item/snow/PinTree.scala",
  "../../src/core/ecs/entities/Item/snow/SnowPile.scala",
  "../../src/core/ecs/entities/Item/snow/SnowyRock.scala",
  "../../src/core/ecs/entities/problems/ChassisProblem.scala",
  "../../src/core/ecs/entities/problems/TemperatureProblem.scala",
  "../../src/core/ecs/entities/problems/TireProblem.scala",
  "../../src/core/ecs/entities/problems/TireSlippageProblem.scala",
  "../../src/core/ecs/systems/Car.scala",
  "../../src/core/ecs/systems/MapGenerator.scala",
  "../../src/core/ecs/systems/track/TrackGeometry.scala",
  "../../src/core/ecs/systems/track/Track.scala",
  "../../src/core/ecs/systems/track/TrackTexture.scala",
  "../../src/core/garage/GarageData.scala",
  "../../src/core/quiz/Question.scala",
  "../../src/core/quiz/QuizData.scala",
  "../../src/core/quiz/Quiz.scala",
  "../../src/core/state/Day.scala",
  "../../src/core/state/GameEvent.scala",
  "../../src/core/state/GameStateMachine.scala",
  "../../src/core/state/GameState.scala",
  "../../src/core/state/Wallet.scala",
  "../../src/core/world/biome/Biome.scala",
  "../../src/core/world/biome/BiomeTexture.scala",
  "../../src/core/world/biome/DesertBiome.scala",
  "../../src/core/world/biome/ForestBiome.scala",
  "../../src/core/world/biome/ParallaxLayer.scala",
  "../../src/core/world/biome/SnowBiome.scala",
  "../../src/core/world/World.scala",
  "../../src/input/DebugInput.scala",
  "../../src/Launcher.scala",
  "../../src/MainWindow.scala",
  "../../src/render/AbstractRenderer.scala",
  "../../src/render/background/BackgroundRenderer.scala",
  "../../src/render/Data.scala",
  "../../src/render/game/GameRenderer.scala",
  "../../src/render/game/ItemsRenderer.scala",
  "../../src/render/game/TrackRenderer.scala",
  "../../src/render/hud/HudRenderer.scala",
  "../../src/render/shaders/Mode7.scala",
  "../../src/render/WorldRenderer.scala",
  "../../src/screens/AbstractScreen.scala",
  "../../src/screens/carSelector/CarSelectorScreen.scala",
  "../../src/screens/cinematic/CinematicScreen.scala",
  "../../src/screens/CustomScreenManager.scala",
  "../../src/screens/death/DeathScreen.scala",
  "../../src/screens/debug/CarDebugScreen.scala",
  "../../src/screens/game/GameScreen.scala",
  "../../src/screens/garage/GarageScreen.scala",
  "../../src/screens/loading/LoadingScreen.scala",
  "../../src/screens/menu/MenuScreen.scala",
  "../../src/screens/quiz/QuizScreen.scala",
  "../../src/ui/components/ButtonGroup.scala",
  "../../src/ui/components/Button.scala",
  "../../src/ui/components/CheckBox.scala",
  "../../src/ui/components/Image.scala",
  "../../src/ui/components/Label.scala",
  "../../src/ui/components/ListItemRow.scala",
  "../../src/ui/components/ProgressBar.scala",
  "../../src/ui/components/SelectBox.scala",
  "../../src/ui/components/Slider.scala",
  "../../src/ui/dialogs/Dialog.scala",
  "../../src/ui/dialogs/SettingsDialog.scala",
  "../../src/ui/hud/CarHUD.scala",
  "../../src/ui/hud/DebugHUD.scala",
  "../../src/ui/hud/SpeedometerHUD.scala",
  "../../src/ui/hud/TemperatureHUD.scala",
  "../../src/ui/hud/WalletHUD.scala",
  "../../src/ui/UISkin.scala",
  "../../src/utils/Common.scala",
  "../../src/utils/Constant.scala",
  "../../src/utils/noise/Generator.scala",
  "../../src/utils/noise/Noise.scala",
  "../../src/utils/noise/Util.scala",
)

#for file in source-files {
  let parts = file.split("/")
  let filename = parts.at(parts.len() - 1)
  [=== #filename
  #code(raw(read(file), lang: "scala"))
  ]
}

=== mode7.glsl
#code(raw(read("../../data/shaders/mode7.glsl"), lang: "glsl"))
