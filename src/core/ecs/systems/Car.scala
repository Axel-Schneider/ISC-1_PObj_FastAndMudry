package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components._

import com.badlogic.gdx.{Gdx, Input}
import utils.Constant.GAME.CAR.FACTOR
import ui.hud.DebugHUD

import ch.hevs.fastandmudry.utils.Constant.GAME.CAR

class Car extends AGameLoop with Orientable with Moveable with Steerable with Temperable with HasTires {
  MaxSpeed = FACTOR.MAX_SPEED
  var isBroken: Boolean = false

  override def onGameLoop(elapsedTime: Float): Unit = {
    var isTurning = false;
    if(Gdx.input.isKeyPressed(Input.Keys.UP))
      Speed += FACTOR.ACCELERATION * elapsedTime
    else
      Speed -= FACTOR.DECELERATION * elapsedTime

    if(Gdx.input.isKeyPressed(leftKey)) {
      WheelAngle -= FACTOR.WHEEL_ROTATION * elapsedTime
      isTurning = true
    }
    if(Gdx.input.isKeyPressed(rightKey)) {
      WheelAngle += FACTOR.WHEEL_ROTATION * elapsedTime
      isTurning = true
    }

    if(IsRightTirePerforated && WheelAngle >= 0){
      WheelAngle += FACTOR.WHEEL_ROTATION * elapsedTime * 0.1f
      isTurning = true
      MaxSpeed = MaxSpeed * 0.5f
    }

    if(IsLeftTirePerforated && WheelAngle <= 0){
      WheelAngle -= FACTOR.WHEEL_ROTATION * elapsedTime * 0.1f
      isTurning = true
      MaxSpeed = MaxSpeed * 0.5f
    }

    if(!isTurning) {
      WheelAngle *= Math.pow(FACTOR.WHEEL_RETURN, elapsedTime.toDouble).toFloat
      if (Math.abs(WheelAngle) < 0.001f) WheelAngle = 0f
    }

    if(WheelAngle > FACTOR.WHEEL_MAX_ANGLE) WheelAngle = FACTOR.WHEEL_MAX_ANGLE
    if(WheelAngle < -FACTOR.WHEEL_MAX_ANGLE) WheelAngle = -FACTOR.WHEEL_MAX_ANGLE

    if(Speed > 0.01f) {
      Rotation += Speed * WheelAngle * elapsedTime
      Rotation %= (Math.PI * 2).toFloat
    }

    Temperature += 0.5f * elapsedTime
    isBroken = checkCarState()


    DebugHUD.setLogVar("Car - Rotation", Rotation)
    DebugHUD.setLogVar("Car - WheelAngle", WheelAngle)
    DebugHUD.setLogVar("Car - Speed", Speed)
    DebugHUD.setLogVar("Car - Temperature", Temperature)
    DebugHUD.setLogVar("Car - Left Tire Perforated", IsLeftTirePerforated)
    DebugHUD.setLogVar("Car - Right Tire Perforated", IsRightTirePerforated)

  }

  def checkCarState(): Boolean = {
    if(Temperature <= CAR.FACTOR.MIN_TEMPERATURE || Temperature >= CAR.FACTOR.MAX_TEMPERATURE) {
      return true
    }

    false
  }
}
