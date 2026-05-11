package ch.hevs.fastandmudry
package render

import ch.hevs.gdx2d.lib.GdxGraphics
import render.background.BackgroundRenderer
import render.game.GameRenderer

class WorldRenderer extends AbstractRenderer {
  private val backgroundRenderer = new BackgroundRenderer;
  private val gameRenderer = new GameRenderer
  override def onGraphicRender(g: GdxGraphics): Unit = {
    backgroundRenderer.onGraphicRender(g)
    gameRenderer.onGraphicRender(g)
  }
}
