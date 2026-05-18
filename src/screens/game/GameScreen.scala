package ch.hevs.fastandmudry
package screens.game

import core.world.World
import render.WorldRenderer
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class GameScreen extends AbstractScreen {
  val WorldRenderer = new WorldRenderer
  override def onInit(): Unit = {  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;
    World.INSTANCE.onGameLoop(ELAPSED_TIME)

    WorldRenderer.onGraphicRender(g)
  }
}
