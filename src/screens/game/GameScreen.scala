package ch.hevs.fastandmudry
package screens.game

import ch.hevs.fastandmudry.core.audio.AudioManager
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.input.DebugInput
import ch.hevs.fastandmudry.render.WorldRenderer
import ch.hevs.fastandmudry.screens.AbstractScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class GameScreen extends AbstractScreen {
  val WorldRender = new WorldRenderer

  override def onInit(): Unit = {
    AudioManager.playTrack(World.INSTANCE.TRACK.biome.musicTrack)
    AudioManager.startEngine()
    WorldRender.onInit()
  }

  override def dispose(): Unit = {
    AudioManager.stopEngine()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    World.INSTANCE.onGameLoop(ELAPSED_TIME)

    WorldRender.onGraphicRender(g)

    g.end()

    DebugInput.debuggingKeyProcess()
  }
}
