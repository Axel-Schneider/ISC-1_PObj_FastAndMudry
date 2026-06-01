package ch.hevs.fastandmudry
package screens.quiz

import core.state.{GameStateMachine, QuizCompleted}
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class QuizScreen extends AbstractScreen {
  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawStringCentered(g.getScreenHeight / 2f, "Quiz")
    renderStage(g, Gdx.graphics.getDeltaTime)

    if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
      GameStateMachine.handle(QuizCompleted)
    }
  }
}
