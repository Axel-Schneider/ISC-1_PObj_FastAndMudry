package ch.hevs.fastandmudry
package userinterface

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.GdxGraphics

class Window extends PortableApplication {
  private var imgBitmap: BitmapImage = null


  override def onInit(): Unit = {
    setTitle("Fast & Mudry")

    // Load a custom image (or from the lib "res/lib/icon64.png")
    imgBitmap = new BitmapImage("data/images/FastAndMudry_logo.png")
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    // Clears the screen
    g.clear()
    // Compute the angle of the image using an elastic interpolation
    val t = computePercentage
    val angle: Float = Interpolation.sine.apply(MIN_ANGLE, MAX_ANGLE, t)

    // Draw everything
    g.drawTransformedPicture(getWindowWidth / 2.0f, getWindowHeight / 2.0f, angle, 0.7f, imgBitmap)
    g.drawStringCentered(getWindowHeight * 0.8f, "Welcome to Fast & Mudry !")
  }

  /**
   * Some animation related variables
   */
  private var direction: Int = 1
  private var currentTime: Float = 0
  final private val ANIMATION_LENGTH: Float = 2f // Animation length (in seconds)
  final private val MIN_ANGLE: Float = -20
  final private val MAX_ANGLE: Float = 20

  /**
   * Compute time percentage for making a looping animation
   *
   * @return the current normalized time
   */
  private def computePercentage: Float = {
    if (direction == 1) {
      currentTime += Gdx.graphics.getDeltaTime
      if (currentTime > ANIMATION_LENGTH) {
        currentTime = ANIMATION_LENGTH
        direction *= -1
      }
    }
    else {
      currentTime -= Gdx.graphics.getDeltaTime
      if (currentTime < 0) {
        currentTime = 0
        direction *= -1
      }
    }
    currentTime / ANIMATION_LENGTH
  }
}
