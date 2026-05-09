package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.lib.GdxGraphics

class GameScreen extends AbstractScreen {

  override def onInit(): Unit = { }

  override def onGraphicRender(g: GdxGraphics): Unit = {g.clear()
    g.clear()
    g.drawStringCentered(g.getScreenHeight - 50, "GAME VIEW !")
  }
}
