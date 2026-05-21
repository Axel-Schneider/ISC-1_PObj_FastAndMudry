package ch.hevs.fastandmudry
package utils

import com.badlogic.gdx.Input

object Constant {
  object Window {
    val WIDTH: Int = 1920
    val HEIGHT: Int = 1080
  }
  object UI {
    object Dialog {
      object SettingsDialog {
        val SIZE_PERCENTAGE: Float = 0.6f
      }
    }
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
      }
    }
  }
  object RENDERING {
    object CAR {
      val WHEEL_MAX_ROTATION = 360
    }
  }
}
