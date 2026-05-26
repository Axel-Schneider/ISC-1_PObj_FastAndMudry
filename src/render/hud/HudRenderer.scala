package ch.hevs.fastandmudry
package render.hud

import render.AbstractRenderer
import core.world.World

import ui.hud.{CarHUD, DebugHUD, SpeedometerHUD}
import ch.hevs.gdx2d.lib.GdxGraphics

class HudRenderer extends AbstractRenderer {
  val Car = World.INSTANCE.CAR

  override def onGraphicRender(g: GdxGraphics): Unit = {
    SpeedometerHUD.draw(g, Car)
    DebugHUD.draw()
    CarHUD.draw(g, Car)
  }
}
