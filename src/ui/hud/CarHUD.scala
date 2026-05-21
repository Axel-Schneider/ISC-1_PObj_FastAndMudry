package ch.hevs.fastandmudry
package ui.hud


import utils.Constant.{Hud, RENDERING}
import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

object CarHUD {
  val STEERING_WHEEL_IMAGE = new BitmapImage("data/images/SteeringWheel.png")

  def draw(g: GdxGraphics, car: Car): Unit = {
    val carPosScreen = g.getScreenWidth / 2f
    g.drawAlphaPicture(carPosScreen, 0, car.WheelAngle * -RENDERING.CAR.WHEEL_MAX_ROTATION, 2, 1, STEERING_WHEEL_IMAGE)
  }
}
