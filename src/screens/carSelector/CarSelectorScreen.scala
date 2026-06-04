package ch.hevs.fastandmudry
package screens.carSelector

import core.state.{BackToMenu, GameStateMachine}
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}

class CarSelectorScreen extends AbstractScreen {
  override def onInit(): Unit = {

  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawStringCentered(g.getScreenHeight / 2f, "CAR SELECTOR")

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      GameStateMachine.handle(BackToMenu)
    }
  }
}
