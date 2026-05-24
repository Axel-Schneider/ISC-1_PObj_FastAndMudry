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
  object MapTexture {
    val MapPadding: Int = 200
  }
  object KEYS {
    val ENABLING_DEBUG_KEY = Input.Keys.F12
  }
}
