package ch.hevs.fastandmudry
package render.background

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.render.AbstractRenderer
import ch.hevs.fastandmudry.render.Data.Game
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

class BackgroundRenderer extends AbstractRenderer {
  override def onGraphicRender(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth
    val screenH = g.getScreenHeight
    val horizon = screenH / 2f
    val biome = World.INSTANCE.TRACK.biome

    g.drawTransformedPicture(screenW / 2f, horizon + (screenH / 4f), 0, screenW, screenH / 2f, biome.skyImage())

    for(layer <- biome.parallaxLayers()) {
      val y = if(layer.isFloor) g.getScreenHeight - 100 else horizon

      drawConstrainedLayer(g, layer.image, Game.Rotation * layer.scrollOffset, y, layer.heightPercentage)
    }
  }

  private def drawConstrainedLayer(g: GdxGraphics, image: BitmapImage, scrollOffset: Float, yBase: Float, heightPercentage: Float): Unit = {
    val texture = image.getImage
    val imgW = texture.getWidth.toFloat
    val imgH = texture.getHeight.toFloat

    val drawH = g.getScreenHeight * heightPercentage
    val drawW = imgW * (drawH / imgH)

    val centerY = yBase + (drawH / 2f)

    val offset = scrollOffset % drawW
    var currentX = offset - drawW

    while (currentX < g.getScreenWidth + drawW) {
      g.drawTransformedPicture(currentX, centerY, 0, drawW, drawH, image)
      currentX += drawW
    }
  }
}
