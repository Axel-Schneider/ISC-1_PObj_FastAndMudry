package ch.hevs.fastandmudry
package render.hud

import render.AbstractRenderer
import core.world.World
import ui.hud.{CarHUD, DebugHUD, SpeedometerHUD}

import ch.hevs.fastandmudry.ui.hud.CarHUD.skin
import ch.hevs.fastandmudry.utils.Constant.RENDERING
import ch.hevs.gdx2d.lib.GdxGraphics

class HudRenderer extends AbstractRenderer {
  private val skin = World.INSTANCE.selectedSkin
  val Car = World.INSTANCE.CAR

  override def onGraphicRender(g: GdxGraphics): Unit = {
    if(!skin.skinBugged) {
      RENDERING.CAR.INTERIOR_SCALE = 1f
      RENDERING.CAR.INTERIOR_VERTICAL_ANCHOR = 0f
    }
    CarHUD.drawInterior(g)
    SpeedometerHUD.draw(g, Car)
    CarHUD.drawSteeringWheel(g, Car)
    DebugHUD.draw()
  }
}
