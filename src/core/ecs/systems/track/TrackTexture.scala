package ch.hevs.fastandmudry
package core.ecs.systems.track

import utils.Constant.MapTexture

import core.world.biome.Biome
import com.badlogic.gdx.graphics.{Color, Pixmap}
import com.badlogic.gdx.graphics.Pixmap.Format

object TrackTexture {
  def generate(geometry: TrackGeometry, biome: Biome): Pixmap = {
    val trackRectangle = geometry.trackSize
    val width = trackRectangle.width.toInt + 2 * MapTexture.MAP_PADDING
    val height = trackRectangle.height.toInt + 2 * MapTexture.MAP_PADDING
    val pixmap = new Pixmap(width, height, Format.RGBA8888)

    biome.prepareTextures(width, height)

    val halfRoadSq = geometry.HalfRoadWidth * geometry.HalfRoadWidth
    val halfLineSq = MapTexture.HALF_LINE_WIDTH * MapTexture.HALF_LINE_WIDTH

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val worldX = trackRectangle.x - MapTexture.MAP_PADDING + x
        val worldY = trackRectangle.y - MapTexture.MAP_PADDING + y
        val distSq = geometry.distToCenterLineSq(worldX, worldY)
        val jitteredShoulder = geometry.HalfShoulderWidth + biome.getShoulderJitter(x, y)
        val halfShoulderSq = jitteredShoulder * jitteredShoulder
        val onRoad = distSq <= halfRoadSq
        val onShoulder = !onRoad && distSq <= halfShoulderSq
        val onCenterLine = distSq <= halfLineSq
        val color = {
          if (onRoad && geometry.isFinishLine(worldX, worldY)) Color.rgba8888(Color.RED)
          else if (onCenterLine) Color.rgba8888(biome.getRoadLineColor())
          else if (onRoad) Color.rgba8888(biome.getRoadColor(x, y))
          else if (onShoulder) Color.rgba8888(biome.getShoulderColor(x, y))
          else Color.rgba8888(biome.getOffRoadColor(x, y))
        }
        pixmap.drawPixel(x, y, color)
      }
    }

    return pixmap
  }
}
