package ch.hevs.fastandmudry
package screens.debug

import core.state.{BackToMenu, GameStateMachine}
import core.world.World
import screens.AbstractScreen
import ui.hud.CarHUD
import utils.Constant.GAME.CAR.FACTOR

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}


class CarDebugScreen extends AbstractScreen {
  private val car = World.INSTANCE.CAR

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    CarHUD.draw(g, car)

    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      GameStateMachine.handle(BackToMenu)
    }
  }
}
