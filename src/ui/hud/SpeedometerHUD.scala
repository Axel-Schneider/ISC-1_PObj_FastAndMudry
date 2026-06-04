package ch.hevs.fastandmudry
package ui.hud

import utils.Constant.Hud
import utils.Constant.GAME.CAR.FACTOR

import core.ecs.systems.Car
import core.world.World
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

object SpeedometerHUD {
  def draw(g: GdxGraphics, car: Car): Unit = {
    val config = World.INSTANCE.selectedSkin.speedometer
    val radius = config.radius
    val angleEvery = (math.abs(Hud.Speedometer.SPEEDOMETER_START_ANGLE) + math.abs(Hud.Speedometer.SPEEDOMETER_END_ANGLE)) / Hud.Speedometer.TICKS_EVERY

    val cx = g.getScreenWidth * config.x
    val cy = g.getScreenHeight * config.y

    // Speedometer background
    g.drawFilledCircle(cx, cy, radius, config.backgroundColor)

    // Speedometer ticks
    var speedLabel = Hud.Speedometer.MAX_SPEED
    for(i <- Hud.Speedometer.SPEEDOMETER_END_ANGLE to Hud.Speedometer.SPEEDOMETER_START_ANGLE by angleEvery) {
      val angle = Math.toRadians(i)
      val mx = math.cos(angle) * (radius - Hud.Speedometer.TICKS_LENGTH/2)  // drawFilledRectangle require the center x,y
      val my = math.sin(angle) * (radius - Hud.Speedometer.TICKS_LENGTH/2)

      val xLabel = math.cos(angle)*(radius-Hud.Speedometer.TICKS_LENGTH-15)
      val yLabel = math.sin(angle)*(radius-Hud.Speedometer.TICKS_LENGTH-15)

      g.drawFilledRectangle(cx + mx.toFloat, cy + my.toFloat, Hud.Speedometer.TICKS_LENGTH, Hud.Speedometer.TICKS_LENGTH/2, i.toFloat, config.ticksColor)

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

    val needleLength = radius - Hud.Speedometer.TICKS_LENGTH
    g.drawFilledRectangle(mx.toFloat, my.toFloat, needleLength, Hud.Speedometer.NEEDLE_WIDTH, angleDeg, config.needleColor)
    g.drawFilledCircle(cx, cy, 20, Color.GRAY)
  }
}
