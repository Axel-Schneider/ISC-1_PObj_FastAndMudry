package ch.hevs.fastandmudry
package screens.game

import core.audio.AudioManager
import core.world.World
import input.DebugInput
import render.WorldRenderer
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class GameScreen extends AbstractScreen {
  val WorldRender = new WorldRenderer

  override def onInit(): Unit = {
    AudioManager.playTrack(World.INSTANCE.TRACK.biome.musicTrack)
    WorldRender.onInit()
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
