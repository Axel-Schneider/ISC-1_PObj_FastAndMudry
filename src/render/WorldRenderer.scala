package ch.hevs.fastandmudry
package render

import render.background.BackgroundRenderer
import render.game.GameRenderer
import render.hud.HudRenderer

import ch.hevs.gdx2d.lib.GdxGraphics

class WorldRenderer extends AbstractRenderer {
  private val backgroundRenderer = new BackgroundRenderer
  private val hudRenderer = new HudRenderer


  override def onInit(): Unit = {
    super.onInit()
    backgroundRenderer.onInit()
    GameRenderer.onInit()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    backgroundRenderer.onGraphicRender(g)
    GameRenderer.onGraphicRender(g)
    hudRenderer.onGraphicRender(g)
  }
}
