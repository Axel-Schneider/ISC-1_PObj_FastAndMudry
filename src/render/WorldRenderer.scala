package ch.hevs.fastandmudry
package render

import ch.hevs.gdx2d.lib.GdxGraphics
import render.background.BackgroundRenderer
import render.game.GameRenderer
import render.hud.HudRenderer

class WorldRenderer extends AbstractRenderer {
  private val backgroundRenderer = new BackgroundRenderer;
  private val gameRenderer = new GameRenderer
  private val hudRenderer = new HudRenderer

  override def onGraphicRender(g: GdxGraphics): Unit = {
    backgroundRenderer.onGraphicRender(g)
    gameRenderer.onGraphicRender(g)
    hudRenderer.onGraphicRender(g)
  }
}
