package ch.hevs.fastandmudry
package screens.debug

import ch.hevs.fastandmudry.core.audio.{AudioManager, MusicTrack}
import ch.hevs.fastandmudry.core.state.{BackToMenu, GameStateMachine}
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.screens.AbstractScreen
import ch.hevs.fastandmudry.ui.hud.{CarHUD, SpeedometerHUD, TemperatureHUD}
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}


class CarDebugScreen extends AbstractScreen {
  private val car = World.INSTANCE.CAR

  override def onInit(): Unit = {
    AudioManager.playTrack(MusicTrack.Menu)
    Gdx.input.setInputProcessor(stage)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    CarHUD.drawInterior(g)
    SpeedometerHUD.draw(g, car)
    CarHUD.drawSteeringWheel(g, car)
    TemperatureHUD.draw(g, car)

    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      GameStateMachine.handle(BackToMenu)
    }
  }
}
