package ch.hevs.fastandmudry
package core.ecs.components

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import core.ecs.abstaction.{Curvable, Dirigible, Drawable, Movable, Positionable}

class Car extends Drawable with Curvable with Movable with Positionable with Dirigible{
  val CAR_WIDTH = 33;
  val CAR_HEIGHT = 100;
  val CAR_MARGIN_BOTTOM = 100;

  override def draw(g: GdxGraphics, x: Float = -1, y: Float = -1): Unit = {
    val carPosScreen = g.getScreenWidth / 2 + (g.getScreenWidth * RoadPosition / 2)
    g.drawFilledRectangle(carPosScreen, CAR_MARGIN_BOTTOM + CAR_HEIGHT, CAR_WIDTH, CAR_HEIGHT, Direction * 30, Color.BLUE)
  }
}
