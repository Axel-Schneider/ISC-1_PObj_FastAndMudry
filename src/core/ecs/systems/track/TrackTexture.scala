package ch.hevs.fastandmudry
package core.ecs.systems.track

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.{Color, Pixmap}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.math.Vector2

object TrackTexture {
  def generate(geometry: TrackGeometry): Pixmap = {
    val width = geometry.trackSize.width.toInt
    val height = geometry.trackSize.height.toInt
    val pixmap = new Pixmap(width, height, Format.RGBA8888)

    for(y <- 0 until height) {
      for(x <- 0 until width) {
        val mapX = geometry.trackSize.x + x
        val mapy = geometry.trackSize.y + y
        val color = if (geometry.isRoad(new Vector2(mapX, mapy))) Color.rgba8888(Color.BLACK) else Color.rgba8888(Color.GRAY)
        pixmap.drawPixel(x, y, color)
      }
    }

    return pixmap
  }
}
