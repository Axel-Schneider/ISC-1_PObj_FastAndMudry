package ch.hevs.fastandmudry

import ch.hevs.fastandmudry.core.audio.AudioManager
import ch.hevs.fastandmudry.screens.CustomScreenManager
import ch.hevs.fastandmudry.utils.Constant.Window
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics

class MainWindow extends PortableApplication(Window.WIDTH, Window.HEIGHT) {
  override def onInit(): Unit = {
    setTitle("Fast & Mudry")
    CustomScreenManager.getInstance.activateScreen(CustomScreenManager.MENU)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    CustomScreenManager.getInstance.render(g);
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    super.onClick(x, y, button)
    CustomScreenManager.getInstance.getActiveScreen.onClick(x,y,button)
  }

  override def onDispose(): Unit = {
    super.onDispose()
    AudioManager.dispose()
  }
}

