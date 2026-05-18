package ch.hevs.fastandmudry
package render

import ch.hevs.gdx2d.lib.GdxGraphics

trait AbstractRenderer {
  def onGraphicRender(g: GdxGraphics): Unit
  def onInit(): Unit = {}
}
