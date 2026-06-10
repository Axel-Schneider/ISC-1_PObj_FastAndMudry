package ch.hevs.fastandmudry
package ui.hud

import utils.Constant.GAME

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import core.ecs.systems.Car

import com.badlogic.gdx.graphics.{Color, Texture}

object TemperatureHUD {
  val thermometerOuter = new Texture("data/images/thermometer.png")
  val PADDING = 20f

  def draw(g: GdxGraphics, car: Car): Unit = {
    val x = Gdx.graphics.getWidth / 1.05f
    val y = Gdx.graphics.getHeight / 20f

    val imgW = thermometerOuter.getWidth.toFloat / 4f
    val imgH = thermometerOuter.getHeight.toFloat / 4f

    val max = math.abs(GAME.CAR.FACTOR.MAX_TEMPERATURE) + math.abs(GAME.CAR.FACTOR.MIN_TEMPERATURE)
    val currentTemp = car.Temperature + max/2

    val tempW = imgW
    var tempH = if(max != 0) currentTemp * imgH / max else 0

    if (tempH > imgH - PADDING) tempH -= PADDING/2

    val color: Color = {
      if(currentTemp < max / 4) Color.BLUE
      else if(currentTemp < max / 2) Color.GREEN
      else if(currentTemp > max - max / 4) Color.RED
      else if(currentTemp > max / 2) Color.YELLOW
      else Color.PURPLE
    }

    g.drawFilledRectangle(x + tempW/2, y + tempH/2, imgW - PADDING, tempH, 0f, color)
    g.draw(thermometerOuter, x, y, imgW, imgH)
  }
}
