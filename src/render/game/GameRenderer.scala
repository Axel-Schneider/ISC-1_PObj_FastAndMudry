package ch.hevs.fastandmudry
package render.game

import render.AbstractRenderer

import ch.hevs.gdx2d.lib.GdxGraphics

class GameRenderer extends AbstractRenderer {
  private val trackRenderer = new TrackRenderer;
  private val carRenderer = new CarRenderer;

  override def onInit(): Unit = {
    super.onInit()
    trackRenderer.onInit()
    carRenderer.onInit()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    trackRenderer.onGraphicRender(g)
    carRenderer.onGraphicRender(g)
  }
}
