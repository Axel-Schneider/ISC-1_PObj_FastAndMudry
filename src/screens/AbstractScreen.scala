package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport

abstract class AbstractScreen extends RenderingScreen {
  protected lazy val LOGO: BitmapImage = new BitmapImage("data/images/FastAndMudry_logo.png")

  // https://libgdx.com/wiki/graphics/2d/scene2d/scene2d
  protected val stage: Stage = new Stage(new ScreenViewport())
  protected def renderStage(g: GdxGraphics, dt: Float): Unit = {
    g.end() // we have to close the first batch (gdx) to open the new batch (scene)
    stage.act(dt)
    stage.draw()
  }
}

