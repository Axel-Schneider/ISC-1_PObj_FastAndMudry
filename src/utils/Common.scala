package ch.hevs.fastandmudry
package utils

import input.DebugInput

object Common {
  object Debugging {
    def IsDebugEnable: Boolean = {
      DebugInput.IsDebugEnable
    }
  }
}
