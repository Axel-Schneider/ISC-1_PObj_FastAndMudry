package ch.hevs.fastandmudry
package utils

import ch.hevs.fastandmudry.input.DebugInput

object Common {
  object Debugging {
    def IsDebugEnable: Boolean = {
      DebugInput.IsDebugEnable
    }
  }
}
