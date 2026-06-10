package ch.hevs.fastandmudry
package render.game

import ch.hevs.fastandmudry.render.AbstractRenderer
import ch.hevs.gdx2d.lib.GdxGraphics

object GameRenderer extends AbstractRenderer {
  override def onInit(): Unit = {
    super.onInit()
    TrackRenderer.onInit()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    TrackRenderer.onGraphicRender(g)
  }
}
