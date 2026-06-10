package ch.hevs.fastandmudry
package render.hud

import render.AbstractRenderer
import core.world.World
import ui.hud.{CarHUD, DebugHUD, SpeedometerHUD, TemperatureHUD}

import ch.hevs.fastandmudry.ui.hud.CarHUD.skin
import ch.hevs.fastandmudry.utils.Constant.RENDERING
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class HudRenderer extends AbstractRenderer {
  private val skin = World.INSTANCE.selectedSkin
  val Car = World.INSTANCE.CAR

  override def onInit(): Unit = {
    super.onInit()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    if(!skin.skinBugged) {
      RENDERING.CAR.INTERIOR_SCALE = 1f
      RENDERING.CAR.INTERIOR_VERTICAL_ANCHOR = 0f
    }
    CarHUD.drawInterior(g)
    SpeedometerHUD.draw(g, Car)
    CarHUD.drawSteeringWheel(g, Car)
    TemperatureHUD.draw(g, Car)
    DebugHUD.draw()
  }
}
