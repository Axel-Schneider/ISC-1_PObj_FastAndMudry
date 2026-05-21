package ch.hevs.fastandmudry
package render.hud

import render.AbstractRenderer
import utils.Constant.Hud
import core.world.World

import ch.hevs.fastandmudry.ui.hud.{DebugHUD, SpeedometerHUD}
import ch.hevs.fastandmudry.utils.Constant.Hud.Speedometer
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class HudRenderer extends AbstractRenderer {
  val Car = World.INSTANCE.CAR

  override def onGraphicRender(g: GdxGraphics): Unit = {
    SpeedometerHUD.draw(g, Car)
    DebugHUD.draw()
  }
}
