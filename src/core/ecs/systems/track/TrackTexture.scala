package ch.hevs.fastandmudry
package core.ecs.systems.track

import utils.Constant.MapTexture
import com.badlogic.gdx.graphics.{Color, Pixmap}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.math.Vector2

object TrackTexture {
  def generate(geometry: TrackGeometry): Pixmap = {
    val trackRectangle = geometry.trackSize
    val width  = trackRectangle.width.toInt  + 2 * MapTexture.MapPadding
    val height = trackRectangle.height.toInt + 2 * MapTexture.MapPadding
    val pixmap = new Pixmap(width, height, Format.RGBA8888)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val worldX = trackRectangle.x - MapTexture.MapPadding + x
        val worldY = trackRectangle.y - MapTexture.MapPadding + y
        val worldP = new Vector2(worldX, worldY)
        val onRoad: Boolean = geometry.isRoad(worldP)
        val color = {
          if (onRoad && geometry.isFinishLine(worldP)) Color.rgba8888(Color.RED)
          else if (onRoad) Color.rgba8888(Color.GRAY)
          else Color.rgba8888(Color.GREEN)
        }
        pixmap.drawPixel(x, y, color)
      }
    }

    return pixmap
  }
}
