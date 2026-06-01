package ch.hevs.fastandmudry
package screens.cinematic

import core.state.{EndDayCinematicEnded, GameState, GameStateMachine, StartDayCinematicEnded}
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class CinematicScreen extends AbstractScreen {
  private var timeElapsed: Float = 0f
  private val DURATION: Float = 5f

  override def onInit(): Unit = {
    timeElapsed = 0f
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    timeElapsed += Gdx.graphics.getDeltaTime
    g.drawStringCentered(g.getScreenHeight / 2f, "Cinematic")

    if (timeElapsed >= DURATION) {
      GameStateMachine.getGameState match {
        case GameState.StartDayCinematic(_) => GameStateMachine.handle(StartDayCinematicEnded)
        case GameState.EndDayCinematic(_) => GameStateMachine.handle(EndDayCinematicEnded)
        case _ => // nothing
      }
    }
  }
}
