package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.screen_management.RenderingScreen

abstract class AbstractScreen extends RenderingScreen {
  protected lazy val LOGO: BitmapImage = new BitmapImage("data/images/FastAndMudry_logo.png")
}

