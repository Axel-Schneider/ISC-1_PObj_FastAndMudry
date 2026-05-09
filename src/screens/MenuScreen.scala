package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.lib.GdxGraphics

class MenuScreen extends AbstractScreen {
  override def onInit(): Unit = {

  }

  override def onGraphicRender(g: GdxGraphics): Unit = {g.clear()
    g.clear()
    g.drawStringCentered(g.getScreenHeight - 50, "BIENVENUE SUR FAST & MUDRY !")
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
    val manager = CustomScreenManager.getInstance

    manager.activateScreen(CustomScreenManager.GAME)
  }
}
