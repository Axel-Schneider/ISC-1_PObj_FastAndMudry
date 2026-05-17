package ch.hevs.fastandmudry
package render.hud

import render.AbstractRenderer

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class HudRenderer extends AbstractRenderer {
  override def onGraphicRender(g: GdxGraphics): Unit = {
    drawSpeedometer(g)
  }

  private def drawSpeedometer(g: GdxGraphics): Unit = {
    val SPEEDOMETER_WIDTH = 120
    val SPEEDOMETER_PADDING = 30
    val SPEEDOMETER_COLOR = Color.CORAL
    val MAX_SPEED = 100
    val TICKS_EVERY = 10
    val SPEEDOMETER_START_ANGLE = 210
    val SPEEDOMETER_END_ANGLE = -30
    val TICKS_LENGTH = 10

    val screenW = g.getScreenWidth
    val angleEvery = (math.abs(SPEEDOMETER_START_ANGLE) + math.abs(SPEEDOMETER_END_ANGLE)) / TICKS_EVERY

    val cx = screenW-(SPEEDOMETER_WIDTH + SPEEDOMETER_PADDING)
    val cy = SPEEDOMETER_WIDTH + SPEEDOMETER_PADDING

    g.drawFilledCircle(cx, cy, SPEEDOMETER_WIDTH, SPEEDOMETER_COLOR)

    var speedLabel = MAX_SPEED
    for(i <- SPEEDOMETER_END_ANGLE to SPEEDOMETER_START_ANGLE by angleEvery) {
      val angle = Math.toRadians(i)

      val xStart: Int = (math.cos(angle)*SPEEDOMETER_WIDTH).toInt
      val yStart: Int = (math.sin(angle)*SPEEDOMETER_WIDTH).toInt
      val xEnd: Int = (math.cos(angle)*(SPEEDOMETER_WIDTH-TICKS_LENGTH)).toInt
      val yEnd: Int = (math.sin(angle)*(SPEEDOMETER_WIDTH-TICKS_LENGTH)).toInt
      val xLabel: Int = (math.cos(angle)*(SPEEDOMETER_WIDTH-TICKS_LENGTH-15)).toInt
      val yLabel: Int = (math.sin(angle)*(SPEEDOMETER_WIDTH-TICKS_LENGTH-15)).toInt

      g.drawLine(cx + xStart, cy + yStart, cx + xEnd, cy + yEnd, Color.YELLOW)
      g.drawString(cx + xLabel, cy + 5 + yLabel, speedLabel.toString, 1)

      speedLabel -= MAX_SPEED / TICKS_EVERY
    }


  }
}
