package ch.hevs.fastandmudry
package render

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.render.background.BackgroundRenderer
import com.badlogic.gdx.graphics.Color

class WorldRenderer extends AbstractRenderer {
  private val backgroundRenderer = new BackgroundRenderer;
  private val trackRenderer = new TrackRenderer;
  private val carRenderer = new CarRenderer;
  override def onGraphicRender(g: GdxGraphics): Unit = {
    backgroundRenderer.onGraphicRender(g, World.INSTANCE.CAR.Distance, World.INSTANCE.TRACK.Curvature)
    trackRenderer.onGraphicRender(g, World.INSTANCE.CAR.Distance, World.INSTANCE.TRACK.Curvature)
    carRenderer.onGraphicRender(g)
  }
}
