package ch.hevs.fastandmudry
package render.game

import render.AbstractRenderer

import render.hud.CarHUD
import ch.hevs.gdx2d.lib.GdxGraphics

class GameRenderer extends AbstractRenderer {
  private val trackRenderer = new TrackRenderer;

  override def onInit(): Unit = {
    super.onInit()
    trackRenderer.onInit()
    CarHUD.onInit()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    trackRenderer.onGraphicRender(g)
    CarHUD.onGraphicRender(g)
  }
}
