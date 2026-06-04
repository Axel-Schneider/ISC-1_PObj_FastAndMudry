package ch.hevs.fastandmudry
package ui.hud


import utils.Constant.RENDERING
import utils.Constant.GAME.CAR.FACTOR
import core.ecs.systems.Car

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

object CarHUD {
  private val skin = World.INSTANCE.selectedSkin
  private val INTERIOR_IMAGE = new BitmapImage(skin.interiorImagePath)
  private val STEERING_WHEEL_IMAGE = new BitmapImage(skin.steeringWheelImagePath)

  def draw(g: GdxGraphics, car: Car): Unit = {
    drawInterior(g)
    drawSteeringWheel(g, car)
  }

  def drawInterior(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = INTERIOR_IMAGE.getImage.getWidth.toFloat
    val imgH = INTERIOR_IMAGE.getImage.getHeight.toFloat

    val scale = math.max(screenW / imgW, screenH / imgH)
    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, scale, INTERIOR_IMAGE)
  }

  def drawSteeringWheel(g: GdxGraphics, car: Car): Unit = {
    val wheelRatio = car.WheelAngle / FACTOR.WHEEL_MAX_ANGLE
    val posX = g.getScreenWidth * skin.wheelPosition.x
    val posY = g.getScreenHeight * skin.wheelPosition.y
    g.drawAlphaPicture(posX, posY, wheelRatio * -RENDERING.CAR.WHEEL_MAX_ROTATION, RENDERING.CAR.WHEEL_SCALE, 1f, STEERING_WHEEL_IMAGE)
  }
}
