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
    val MAP_PADDING: Int = 200
    val HALF_ROAD_WIDTH: Float = 30f
    val SHOULDER_WIDTH: Float = 15f
    val HALF_SHOULDER_WIDTH: Float = HALF_ROAD_WIDTH + SHOULDER_WIDTH
    val HALF_LINE_WIDTH: Float = 2f
    val SHOULDER_JITTER: Float = 32f
    val SHOULDER_JITTER_NOISE_CELL: Int = 32
    object ITEMS {
      object SIMPLE_TREE {
        val RENDERING_FACTOR = 100
        val IMAGE_SOURCE = "data/images/item/SimpleTree.png"
      }
      object SIMPLE_ROCK {
        val RENDERING_FACTOR = 2
        val IMAGE_SOURCE = "data/images/item/SimpleRock.png"
      }
    }
  }
  object KEYS {
    val ENABLING_DEBUG_KEY = Input.Keys.F12
  }
  object QUIZ {
    val COINS_PER_CORRECT_ANSWER: Int = 10
    val REVEAL_DURATION: Float = 3f
    val BUTTON_WIDTH: Float = 300
    val BUTTON_HEIGHT: Float = 80
    val GRID_GAP: Float = 30
    val BACKGROUND_IMAGE: String = "data/images/quiz/bg.png"
  }
  object GARAGE {
    val LIST_WIDTH: Float = 600
    val LIST_PADDING: Float = 40
    val ROW_GAP: Float = 15
    val BACKGROUND_IMAGE: String = "data/images/garage/bg.png"
    val CAR_IMAGE: String = "data/images/garage/car.png"
  }
  object CINEMATIC {
    val CAR_IMAGE: String = "data/images/cinematic/car.png"
    val FINAL_BACKGROUND_IMAGE: String = "data/images/cinematic/final/bg.png"
  }
  object GAME {
    object CAR {
      object FACTOR {
        val ACCELERATION = 15f
        val DECELERATION = 7f
        val SPEED = 5f
        val WHEEL_ROTATION = 0.05f
        val WHEEL_RETURN = 0.01f
        val WHEEL_MAX_ANGLE = 0.05f
        val MAX_SPEED = 60f
        val MAX_TEMPERATURE = 100f
        val MIN_TEMPERATURE = -100f
      }
    }
  }
  object RENDERING {
    object CAR {
      val WHEEL_MAX_ROTATION = 180
    }
  }
}
