package ch.hevs.fastandmudry
package ui.hud

import utils.Constant.Hud
import utils.Constant.RENDERING
import utils.Constant.GAME.CAR.FACTOR

import core.ecs.systems.Car
import core.world.World
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

object SpeedometerHUD {
  def draw(g: GdxGraphics, car: Car): Unit = {
    val config = World.INSTANCE.selectedSkin.speedometer
    val zoom = RENDERING.CAR.INTERIOR_SCALE
    val radius = config.radius * zoom
    val ticksLength = Hud.Speedometer.TICKS_LENGTH * zoom
    val needleWidth = Hud.Speedometer.NEEDLE_WIDTH * zoom
    val labelInset = 15f * zoom
    val angleEvery = (math.abs(Hud.Speedometer.SPEEDOMETER_START_ANGLE) + math.abs(Hud.Speedometer.SPEEDOMETER_END_ANGLE)) / Hud.Speedometer.TICKS_EVERY

    val screenCx = g.getScreenWidth / 2f
    val screenCy = g.getScreenHeight / 2f
    val anchorY = g.getScreenHeight * RENDERING.CAR.INTERIOR_VERTICAL_ANCHOR
    val cx = screenCx + (g.getScreenWidth * config.x - screenCx) * zoom
    val cy = screenCy + (g.getScreenHeight * config.y - screenCy) * zoom - anchorY

    // Speedometer background
    g.drawFilledCircle(cx, cy, radius, config.backgroundColor)

    // Speedometer ticks
    var speedLabel = Hud.Speedometer.MAX_SPEED
    for(i <- Hud.Speedometer.SPEEDOMETER_END_ANGLE to Hud.Speedometer.SPEEDOMETER_START_ANGLE by angleEvery) {
      val angle = Math.toRadians(i)
      val mx = math.cos(angle) * (radius - ticksLength/2)  // drawFilledRectangle require the center x,y
      val my = math.sin(angle) * (radius - ticksLength/2)

      val xLabel = math.cos(angle)*(radius-ticksLength-labelInset)
      val yLabel = math.sin(angle)*(radius-ticksLength-labelInset)

      g.drawFilledRectangle(cx + mx.toFloat, cy + my.toFloat, ticksLength, ticksLength/2, i.toFloat, config.ticksColor)

      g.drawString(cx + xLabel.toFloat, cy + 5 + yLabel.toFloat, speedLabel.toString, 1)  // "1" value is to align it correctly
      speedLabel -= Hud.Speedometer.MAX_SPEED / Hud.Speedometer.TICKS_EVERY
    }

    // Speedometer needle
    val carSpeedInKmH = (Hud.Speedometer.MAX_SPEED * car.Speed) / FACTOR.MAX_SPEED
    val angleDeg = Hud.Speedometer.SPEEDOMETER_START_ANGLE + (carSpeedInKmH * (Hud.Speedometer.SPEEDOMETER_END_ANGLE - Hud.Speedometer.SPEEDOMETER_START_ANGLE)) / Hud.Speedometer.MAX_SPEED
    val angleRad = Math.toRadians(angleDeg)

    val xStart = math.cos(angleRad)*radius
    val yStart = math.sin(angleRad)*radius

    val mx = cx + xStart / 2
    val my = cy + yStart / 2

    val needleLength = radius - ticksLength
    g.drawFilledRectangle(mx.toFloat, my.toFloat, needleLength, needleWidth, angleDeg, config.needleColor)
    g.drawFilledCircle(cx, cy, 20 * zoom, Color.GRAY)
  }
}
