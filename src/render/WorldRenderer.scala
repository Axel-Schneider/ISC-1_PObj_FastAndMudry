package ch.hevs.fastandmudry
package render

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.fastandmudry.core.world.World
import com.badlogic.gdx.graphics.Color

class WorldRenderer extends AbstractRenderer {
  private val backgroundRenderer = new BackgroundRenderer;
  private val trackRenderer = new TrackRenderer;
  private val carRenderer = new CarRenderer;
  override def onGraphicRender(g: GdxGraphics): Unit = {
    backgroundRenderer.onGraphicRender(g)
    trackRenderer.onGraphicRender(g)
    carRenderer.onGraphicRender(g)
  }
}
