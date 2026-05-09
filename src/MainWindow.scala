package ch.hevs.fastandmudry

import ch.hevs.fastandmudry.screens.LoadingScreen
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}

class MainWindow extends PortableApplication {

  private val s = new ScreenManager

  override def onInit(): Unit = {
    setTitle("Fast & Mudry")
    s.registerScreen(classOf[LoadingScreen])
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    s.render(g);
  }
}

