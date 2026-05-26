package ch.hevs.fastandmudry
package ui.hud

import utils.Constant.Hud
import utils.Constant.GAME.CAR.FACTOR

import core.ecs.systems.Car
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

object SpeedometerHUD {
  def draw(g: GdxGraphics, car: Car): Unit = {
    val screenW = g.getScreenWidth
    val angleEvery = (math.abs(Hud.Speedometer.SPEEDOMETER_START_ANGLE) + math.abs(Hud.Speedometer.SPEEDOMETER_END_ANGLE)) / Hud.Speedometer.TICKS_EVERY

    val cx = screenW-(Hud.Speedometer.SPEEDOMETER_WIDTH + Hud.Speedometer.SPEEDOMETER_PADDING)
    val cy = Hud.Speedometer.SPEEDOMETER_WIDTH + Hud.Speedometer.SPEEDOMETER_PADDING

    // Speedometer background
    g.drawFilledCircle(cx, cy, Hud.Speedometer.SPEEDOMETER_WIDTH, Hud.Speedometer.SPEEDOMETER_COLOR)

    // Speedometer ticks
    var speedLabel = Hud.Speedometer.MAX_SPEED
    for(i <- Hud.Speedometer.SPEEDOMETER_END_ANGLE to Hud.Speedometer.SPEEDOMETER_START_ANGLE by angleEvery) {
      val angle = Math.toRadians(i)
      val mx = math.cos(angle) * (Hud.Speedometer.SPEEDOMETER_WIDTH - Hud.Speedometer.TICKS_LENGTH/2)  // drawFilledRectangle require the center x,y
      val my = math.sin(angle) * (Hud.Speedometer.SPEEDOMETER_WIDTH - Hud.Speedometer.TICKS_LENGTH/2)

      val xLabel = math.cos(angle)*(Hud.Speedometer.SPEEDOMETER_WIDTH-Hud.Speedometer.TICKS_LENGTH-15)
      val yLabel = math.sin(angle)*(Hud.Speedometer.SPEEDOMETER_WIDTH-Hud.Speedometer.TICKS_LENGTH-15)

      g.drawFilledRectangle(cx + mx.toFloat, cy + my.toFloat, Hud.Speedometer.TICKS_LENGTH, Hud.Speedometer.TICKS_LENGTH/2, i.toFloat, Hud.Speedometer.TICKS_COLOR)

      g.drawString(cx + xLabel.toFloat, cy + 5 + yLabel.toFloat, speedLabel.toString, 1)  // "1" value is to align it correctly
      speedLabel -= Hud.Speedometer.MAX_SPEED / Hud.Speedometer.TICKS_EVERY
    }

    // Speedometer needle
    val carSpeedInKmH = (Hud.Speedometer.MAX_SPEED * car.Speed) / FACTOR.MAX_SPEED
    val angleDeg = Hud.Speedometer.SPEEDOMETER_START_ANGLE + (carSpeedInKmH * (Hud.Speedometer.SPEEDOMETER_END_ANGLE - Hud.Speedometer.SPEEDOMETER_START_ANGLE)) / Hud.Speedometer.MAX_SPEED
    val angleRad = Math.toRadians(angleDeg)

    val xStart = math.cos(angleRad)*Hud.Speedometer.SPEEDOMETER_WIDTH
    val yStart = math.sin(angleRad)*Hud.Speedometer.SPEEDOMETER_WIDTH

    val mx = cx + xStart / 2
    val my = cy + yStart / 2

    val needleLength = Hud.Speedometer.SPEEDOMETER_WIDTH - Hud.Speedometer.TICKS_LENGTH
    g.drawFilledRectangle(mx.toFloat, my.toFloat, needleLength, Hud.Speedometer.NEEDLE_WIDTH, angleDeg, Hud.Speedometer.NEEDLE_COLOR)
    g.drawFilledCircle(cx, cy, 20, Color.GRAY)
  }
}
