package ch.hevs.fastandmudry
package render.game

import core.world.World
import render.AbstractRenderer

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class CarRenderer extends AbstractRenderer {
  val CAR_WIDTH = 33;
  val CAR_HEIGHT = 100;
  val CAR_MARGIN_BOTTOM = 100;
  private val Car = World.INSTANCE.CAR
  override def onGraphicRender(g: GdxGraphics): Unit = {
    val carPosScreen = g.getScreenWidth / 2
    g.drawFilledRectangle(carPosScreen, CAR_MARGIN_BOTTOM + CAR_HEIGHT, CAR_WIDTH, CAR_HEIGHT, Car.WheelAngle * 30, Color.BLUE)
  }
}
