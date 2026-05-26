package ch.hevs.fastandmudry
package ui.hud


import utils.Constant.{Hud, RENDERING}
import utils.Constant.GAME.CAR.FACTOR
import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

object CarHUD {
  val STEERING_WHEEL_IMAGE = new BitmapImage("data/images/SteeringWheel.png")

  def draw(g: GdxGraphics, car: Car): Unit = {
    val carPosScreen = g.getScreenWidth / 2f
    val wheelRatio = car.WheelAngle / FACTOR.WHEEL_MAX_ANGLE
    g.drawAlphaPicture(carPosScreen, 0, wheelRatio * -RENDERING.CAR.WHEEL_MAX_ROTATION, 2, 1, STEERING_WHEEL_IMAGE)
  }
}
