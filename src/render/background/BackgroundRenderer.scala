package ch.hevs.fastandmudry
package render.background

import render.AbstractPerspectiveRenderer

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics

class BackgroundRenderer extends AbstractPerspectiveRenderer {
  private val skyImage = new BitmapImage("data/parallax/skies/sky_sky.png")
  private val backMountainImage = new BitmapImage("data/parallax/skies/sky_back_mountain.png")
  private val cloudFloorImage = new BitmapImage("data/parallax/skies/sky_cloud_floor.png")
  private val cloudsImage = new BitmapImage("data/parallax/skies/sky_clouds.png")
  private val frontMountainImage = new BitmapImage("data/parallax/skies/sky_front_mountain.png")
  override def onGraphicRender(g: GdxGraphics, distance: Float, curvature: Float): Unit = {
    val screenW = g.getScreenWidth
    val screenH = g.getScreenHeight
    val horizon = screenH / 2f

    g.drawTransformedPicture(screenW / 2f, horizon + (screenH / 4f), 0, screenW, screenH / 2f, skyImage)

    drawConstrainedLayer(g, backMountainImage, curvature * -400f, horizon, 0.30f)
    drawConstrainedLayer(g, cloudsImage, curvature * -800f, horizon, 0.20f)
    drawConstrainedLayer(g, frontMountainImage, curvature * -1500f, horizon, 0.3f)
    drawConstrainedLayer(g, cloudFloorImage, curvature * -2500f, g.getScreenHeight - 100, 0.15f)
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
