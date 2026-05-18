package ch.hevs.fastandmudry
package screens.game

import core.world.World
import render.WorldRenderer
import screens.AbstractScreen

import core.world.World

import ch.hevs.fastandmudry.render.WorldRenderer
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.{Vector2, Vector3}

class GameScreen extends AbstractScreen {
  val WorldRender = new WorldRenderer

  override def onInit(): Unit = {
    WorldRender.onInit()
  }


  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    World.INSTANCE.onGameLoop(ELAPSED_TIME)

    WorldRender.onGraphicRender(g)

    g.end()
  }
}
