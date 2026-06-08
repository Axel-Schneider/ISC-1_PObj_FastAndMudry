package ch.hevs.fastandmudry
package utils

import com.badlogic.gdx.Input

object Constant {
  object Window {
    val WIDTH: Int = 1920
    val HEIGHT: Int = 1080
  }
  object MENU {
    val BACKGROUND_IMAGE = "data/images/menu_bg.png"
  }
  object Hud {
    object Speedometer {
      val MAX_SPEED: Int = 100
      val TICKS_EVERY: Int = 10
      val SPEEDOMETER_START_ANGLE: Int = 210
      val SPEEDOMETER_END_ANGLE: Int = -30

      val TICKS_LENGTH: Float = 10f
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
    val COINS_PER_CORRECT_ANSWER: Int = 100
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
  }
  object CAR_SELECTOR {
    val BACKGROUND_IMAGE: String = "data/images/car_selector_bg.png"
    val BUTTON_WIDTH: Float = 120
    val BUTTON_HEIGHT: Float = 120
    val SIDE_PADDING: Float = 80
    val TOP_BOTTOM_PADDING: Float = 60
    val SELECT_BUTTON_WIDTH: Float = 300
    val SELECT_BUTTON_HEIGHT: Float = 80
    val EXIT_BUTTON_WIDTH: Float = 200
    val EXIT_BUTTON_HEIGHT: Float = 80
    val CAR_VERTICAL_OFFSET: Float = 150
    val FONT_SIZE: Float = 3f
  }
  object CINEMATIC {
    val FRONT_FINAL_BACKGROUND_IMAGE: String = "data/images/cinematic/final/front_bg.png"
    val BACK_FINAL_BACKGROUND_IMAGE: String = "data/images/cinematic/final/back_bg.png"
    val MUDRY_BACKGROUND_IMAGE: String = "data/images/cinematic/final/mudry.png"
  }
  object DEATH {
    val BACKGROUND_IMAGE: String = "data/images/death_bg.png"
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
      val WHEEL_SCALE = 1.75f
      val INTERIOR_SCALE = 1.3f
      val INTERIOR_VERTICAL_ANCHOR = 0.2f
    }
  }
}
