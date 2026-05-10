package ch.hevs.fastandmudry
package render

import ch.hevs.gdx2d.lib.GdxGraphics

abstract class AbstractPerspectiveRenderer extends AbstractRenderer {
  override final def onGraphicRender(g: GdxGraphics): Unit = {
    onGraphicRender(g, 0f, 0f)
  }

  def onGraphicRender(g: GdxGraphics, distance: Float, curvature: Float): Unit
}
