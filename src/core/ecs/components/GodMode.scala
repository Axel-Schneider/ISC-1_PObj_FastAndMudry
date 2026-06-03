package ch.hevs.fastandmudry.core.ecs.components

import com.badlogic.gdx.{Gdx, Input}

trait GodMode {
  private var _IsGodModeEnable: Boolean = false
  private var isPressed = false

  def IsGodModeEnable: Boolean = _IsGodModeEnable

  def checkGodMode(): Unit = {
    if (Gdx.input.isKeyPressed(Input.Keys.F8)) {
      if(!isPressed) {
        _IsGodModeEnable = !_IsGodModeEnable
        isPressed = true
      }
    } else {
      isPressed = false
    }
  }
}
