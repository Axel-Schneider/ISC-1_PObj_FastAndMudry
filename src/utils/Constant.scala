package ch.hevs.fastandmudry
package utils

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.Input

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
  object UI {
    object Dialog {
      object SettingsDialog {
        val SIZE_PERCENTAGE: Float = 0.6f
      }
    }
  }
  object MapTexture {
    val MapPadding: Int = 200
  }
  object KEYS {
    val ENABLING_DEBUG_KEY = Input.Keys.F12
  }
  object GAME {
    object CAR {
      object FACTOR {
        val ACCELERATION = 2f
        val DECELERATION = 1f
        val SPEED = 5f
        val WHEEL_ROTATION = 2f
        val WHEEL_RETURN = 0.1f
        val MAX_SPEED = 15f
      }
    }
  }
  object RENDERING {
    object CAR {
      val WHEEL_MAX_ROTATION = 360
    }
  }
}
