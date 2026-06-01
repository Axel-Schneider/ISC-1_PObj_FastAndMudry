package ch.hevs.fastandmudry
package core.state

import screens.CustomScreenManager

object GameStateMachine {
  private var currentState: GameState = GameState.Menu

  def getGameState: GameState = currentState

  def handle(event: GameEvent): Unit = {
    val nextState = currentState.next(event)
    if (nextState != currentState) {
      println(s"From $currentState to $nextState by $event")
      currentState = nextState
      onHandleNextState(nextState)
    } else {
      println(s"Stayed in $currentState ignoring $event")
    }
  }

  private def onHandleNextState(state: GameState): Unit = {
    val csm = CustomScreenManager.getInstance
    state match {
      case GameState.Menu => csm.activateScreen(CustomScreenManager.MENU)
      case GameState.Loading(_) => csm.activateScreen(CustomScreenManager.LOADING)
      case GameState.Playing(_) => csm.activateScreen(CustomScreenManager.GAME)
      case GameState.StartDayCinematic(_) => csm.activateScreen(CustomScreenManager.CINEMATIC)
      case GameState.EndDayCinematic(_) => csm.activateScreen(CustomScreenManager.CINEMATIC)
      case GameState.Quiz(_) => csm.activateScreen(CustomScreenManager.QUIZ)
      case GameState.FinalCinematic => csm.activateScreen(CustomScreenManager.CINEMATIC)
      case GameState.Dead => csm.activateScreen(CustomScreenManager.DEATH)
      case GameState.Finished => csm.activateScreen(CustomScreenManager.MENU)
    }
  }
}
