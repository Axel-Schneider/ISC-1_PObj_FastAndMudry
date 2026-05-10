package ch.hevs.fastandmudry
package screens

import ch.hevs.fastandmudry.core.ecs.systems.{Car, Track}
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.render.WorldRenderer
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ArrayBuffer

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
