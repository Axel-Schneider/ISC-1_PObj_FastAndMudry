package ch.hevs.fastandmudry
package ui.hud


import ch.hevs.fastandmudry.core.ecs.systems.Car
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.utils.Constant.GAME.CAR.FACTOR
import ch.hevs.fastandmudry.utils.Constant.RENDERING
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

    val scale = math.max(screenW / imgW, screenH / imgH) * RENDERING.CAR.INTERIOR_SCALE
    val anchorY = screenH * RENDERING.CAR.INTERIOR_VERTICAL_ANCHOR
    g.drawTransformedPicture(screenW / 2f, screenH / 2f - anchorY, 0f, scale, INTERIOR_IMAGE)
  }

  def drawSteeringWheel(g: GdxGraphics, car: Car): Unit = {
    val wheelRatio = car.WheelAngle / FACTOR.WHEEL_MAX_ANGLE
    val zoom = RENDERING.CAR.INTERIOR_SCALE
    val cx = g.getScreenWidth / 2f
    val cy = g.getScreenHeight / 2f
    val anchorY = g.getScreenHeight * RENDERING.CAR.INTERIOR_VERTICAL_ANCHOR
    val posX = cx + (g.getScreenWidth * skin.wheelPosition.x - cx) * zoom
    val posY = cy + (g.getScreenHeight * skin.wheelPosition.y - cy) * zoom - anchorY
    g.drawAlphaPicture(posX, posY, wheelRatio * -RENDERING.CAR.WHEEL_MAX_ROTATION, RENDERING.CAR.WHEEL_SCALE * zoom, 1f, STEERING_WHEEL_IMAGE)
  }
}
