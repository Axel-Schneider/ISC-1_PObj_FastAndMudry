package ch.hevs.fastandmudry
package screens.death

import core.audio.{AudioManager, MusicTrack}
import core.state.{BackToMenu, GameStateMachine}
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}

class DeathScreen extends AbstractScreen {
  override def onInit(): Unit = {
    AudioManager.playTrack(MusicTrack.Menu)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawStringCentered(g.getScreenHeight / 2f, "DEAD")

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      GameStateMachine.handle(BackToMenu)
    }
  }
}
