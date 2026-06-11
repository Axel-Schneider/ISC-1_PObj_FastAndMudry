#import "@preview/isc-hei-report:0.8.1" : *

= La machine à états

Le jeu est composé de plusieurs phases bien distinctes : le menu, le chargement, la course, les cinématiques, le quiz, le garage... Au début, les changements d'écrans étaient fait un peu à la main, mais on s'est vite rendu compte que ça devenait difficile de savoir qui avait le droit de passer où. Pour mettre de l'ordre, nous avons centralisé toute cette logique dans une machine à état.

== Les états et les évènements

Chaque phase du jeu est représentée par un état, défini dans le trait scellé GameState. On y retrouve par exemple Menu, Loading, Playing, Quiz, Garage, Dead ou encore FinalCinematic. Certains états transportent en plus le jour courant de la partie (Day1, Day2 ou Day3), ce qui permet de savoir où le joueur en est dans sa progression sans avoir besoin d'une variable globale à côté.

Les transitions sont déclenchées par des évenements, comme StartGame, MapLoaded, FinishLineCrossed ou CarBroke. L'idée centrale est que chaque état décide lui même de sa transition : il implémente une méthode next qui reçoit un évènement et retourne le prochain état. Si l'évènement ne le concerne pas, l'état se retourne simplement lui-même et rien ne se passe.

#figure(code()[
```scala
final case class Playing(day: Day) extends GameState {
  def next(event: GameEvent): GameState = {
    event match {
      case FinishLineCrossed if day.next.isEmpty => FinalCinematic
      case FinishLineCrossed => EndDayCinematic(day)
      case CarBroke => Dead
      case _ => this
    }
  }
}
```
], caption: [L'état Playing et ses transitions possibles])

== Le fonctionnement

L'objet GameStateMachine garde en mémoire l'état courant. Quand un évènement arrive, il demande à l'état courant quel est le prochain état. Si l'état change, la machine active l'écran qui correspond via le gestionnaire d'écrans : l'état Menu affiche l'écran du menu, l'état Playing affiche l'écran de jeu, et ainsi de suite. Si l'état ne change pas, l'évènement est simplement ignoré. C'est un gros avantage : un évènement envoyé au mauvais moment ne peut pas casser le jeu ni provoquer des comportements bizarre.

Les évènements sont envoyés depuis un peu partout dans le code. Les boutons du menu envoient StartGame ou OpenCarSelector, l'écran de chargement envoie MapLoaded quand la carte est prête, la piste envoie FinishLineCrossed quand la voiture franchit la ligne d'arrivée ou CarBroke quand le véhicule est cassé, et les cinématiques envoient leur évènement de fin une fois leur durée écoulée. Aucun de ces endroits n'a besoin de connaître l'écran suivant, ils signalent juste ce qui vient de se passer.

Une partie complète suit donc un cycle assez naturel : depuis le menu, on passe au chargement puis à la course du premier jour. Quand la ligne d'arrivée est franchie, une cinématique de fin de journée se lance, suivie du quiz puis du garage où le joueur peut réparer sa voiture. Une cinématique de début de journée enchaîne ensuite sur le chargement du jour suivant. Au troisième jour, franchir la ligne d'arrivée déclenche la cinématique finale qui ramène au menu. Et si la voiture casse en route, peu importe le jour, on passe directement à l'écran de mort.

Ce découpage nous a beaucoup aidé sur la fin du projet : pour rajouter une nouvelle phase, comme le sélecteur de voiture, il suffisait de rajouter un état, ses évènements et son écran, sans toucher au restant de la logique.
