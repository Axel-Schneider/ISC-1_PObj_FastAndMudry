package ch.hevs.fastandmudry
package utils

object Constant {
  object Window {
    val WIDTH: Int = 1920
    val HEIGHT: Int = 1080
  }
  object Game {
    object View {
      val Distance = 200f
      object Track {
        val BASE_MIDDLE_POINT = 0.5f
        val MIN_ROAD_WIDTH_PERCENTAGE = 0.01F
        val ROAD_WIDTH_PERCENTAGE = 0.8f
        val CLIP_WIDTH_PERCENTAGE = 0.15f
        val WAVE_FREQUENCY = 30f
      }
      object Car {
        val MARGIN_BOTTOM = 100f
      }
    }
  }
}
