package ch.hevs.fastandmudry
package render.hud

import core.world.World
import render.AbstractRenderer

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

class CarHUD extends AbstractRenderer {
  private val Car = World.INSTANCE.CAR
  val StreeringWheel = new BitmapImage("data/images/StreeringWheel.png")

  override def onGraphicRender(g: GdxGraphics): Unit = {
    val carPosScreen = g.getScreenWidth / 2f
    g.drawAlphaPicture(carPosScreen, 0, Car.WheelAngle * -30, 2, 1, StreeringWheel)
  }
}
