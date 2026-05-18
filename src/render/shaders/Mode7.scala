package ch.hevs.fastandmudry
package render.shaders

import com.badlogic.gdx.math.Vector3

object Mode7 {
  val SHADER_PATH = "data/shaders/mode7.glsl"
  object Parameter {
    object KEY {
      val ENABLE = "enabled"
      object CAMERA {
        val POSITION = "cameraPosition"
        val AXIS = "cameraAxis"
        val ANGLE = "cameraAngle"
      }
      object SCREEN {
        object PLAN {
          val DISTANCE = "screenPlanDistance"
        }
      }
      val RESOLUTION = "resolution"
      val PITCH = "pitch"
    }
  }
  object DEFAULT_VALUES {
    val ENABLE = true
    object CAMERA {
      val FOV: Float = Math.PI.toFloat / 4f;
      val ANGLE = 0f
      val POSITION = new Vector3(0f, 0f, 0.5f)
      val AXIS = new Vector3(0f, -0f, -0.1f)
    }
    val PITCH = 0f
  }
}
