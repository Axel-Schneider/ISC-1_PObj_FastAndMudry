package ch.hevs.fastandmudry
package screens.garage

import core.state.{GarageReady, GameStateMachine}
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}

class GarageScreen extends AbstractScreen {
  override def onInit(): Unit = {

  }
  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawStringCentered(g.getScreenHeight / 2f, "Garage")

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      GameStateMachine.handle(GarageReady)
    }
  }
}
