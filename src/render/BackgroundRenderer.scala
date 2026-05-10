package ch.hevs.fastandmudry
package render

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class BackgroundRenderer extends AbstractRenderer {

  override def onGraphicRender(g: GdxGraphics): Unit = {
    val screen12 = g.getScreenHeight * 0.5f
    val screen34 = g.getScreenHeight * 0.75f
    for(x <- 0 to g.getScreenWidth) {
      val hillHeight = math.abs(math.sin(x * 0.005f + World.INSTANCE.TRACK.Curvature*2f) * 100f).toFloat
      g.drawLine(x, g.getScreenHeight, x, screen34, Color.NAVY)
      g.drawLine(x, screen34, x, screen12, Color.BLUE)
      g.drawLine(x, screen12+hillHeight, x, screen12, Color.OLIVE)
    }
  }
}
