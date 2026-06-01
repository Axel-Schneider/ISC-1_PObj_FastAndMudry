package ch.hevs.fastandmudry
package core.state

// State machine structure taken from : https://www.scala-algorithms.com/StateMachine/
sealed trait GameState {
  def next(event: GameEvent): GameState
}

object GameState {
  case object Menu extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case StartGame => Loading(Day1)
        // by default, does nothing, stay in Menu
        case _ => this
      }
    }
  }

  case object Dead extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case BackToMenu => Menu
        case _ => this
      }
    }
  }

  final case class Loading(day: Day) extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case MapLoaded => Playing(day)
        case _ => this
      }
    }
  }

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

  case object FinalCinematic extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case FinalCinematicEnded => Menu
        case _ => this
      }
    }
  }

  final case class EndDayCinematic(day: Day) extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case EndDayCinematicEnded => Quiz(day)
        case _ => this
      }
    }
  }

  final case class StartDayCinematic(day: Day) extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case StartDayCinematicEnded => Loading(day)
        case _ => this
      }
    }
  }

  final case class Quiz(day: Day) extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case QuizCompleted => Garage(day)
        case _ => this
      }
    }
  }

  final case class Garage(day: Day) extends GameState {
    def next(event: GameEvent): GameState = {
      event match {
        case GarageReady => StartDayCinematic(day.next.get)
        case _ => this
      }
    }
  }
}
