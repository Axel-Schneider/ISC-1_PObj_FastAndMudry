package ch.hevs.fastandmudry
package input

import utils.Constant

import com.badlogic.gdx.Gdx

object DebugInput {
  private var isSetupKeyPressing = false
  private var _isDebugEnable = false
  def IsDebugEnable: Boolean = _isDebugEnable

  def debuggingKeyProcess(): Unit = {
    if (Gdx.input.isKeyPressed(Constant.KEYS.ENABLING_DEBUG_KEY)) {
      if (!isSetupKeyPressing) {
        isSetupKeyPressing = true
        _isDebugEnable = !_isDebugEnable
      }
    } else isSetupKeyPressing = false
  }
}
