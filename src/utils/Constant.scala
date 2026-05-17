package ch.hevs.fastandmudry
package utils

import com.badlogic.gdx.graphics.Color

object Constant {
  object Window {
    val WIDTH: Int = 1920
    val HEIGHT: Int = 1080
  }
  object Hud {
    object Speedometer {
      val SPEEDOMETER_WIDTH: Float = 120f
      val SPEEDOMETER_PADDING: Float = 30f
      val SPEEDOMETER_COLOR: Color = Color.BLACK

      val MAX_SPEED: Int = 100
      val TICKS_EVERY: Int = 10
      val SPEEDOMETER_START_ANGLE: Int = 210
      val SPEEDOMETER_END_ANGLE: Int = -30

      val TICKS_LENGTH: Float = 10f
      val TICKS_COLOR: Color = Color.YELLOW

      val NEEDLE_COLOR: Color = Color.RED
      val NEEDLE_WIDTH: Float = 3f
    }
  }
}
