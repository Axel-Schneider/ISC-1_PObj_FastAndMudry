package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components._

import render.hud.DebugHUD
import com.badlogic.gdx.{Gdx, Input}
import utils.Constant.GAME.CAR.FACTOR

class Car extends AGameLoop with Orientable with Moveable with Steerable {
  override def onGameLoop(elapsedTime: Float): Unit = {
    var isTurning = false;
    if(Gdx.input.isKeyPressed(Input.Keys.UP))
      Speed += FACTOR.ACCELERATION * elapsedTime
    else
      Speed -= FACTOR.DECELERATION * elapsedTime

    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      WheelAngle -= FACTOR.WHEEL_ROTATION * elapsedTime
      isTurning = true
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      WheelAngle += FACTOR.WHEEL_ROTATION * elapsedTime
      isTurning = true
    }

    if(!isTurning) {
      WheelAngle *= Math.pow(FACTOR.WHEEL_RETURN, elapsedTime.toDouble).toFloat
      if (Math.abs(WheelAngle) < 0.001f) WheelAngle = 0f
    }

    if(WheelAngle > 1) WheelAngle = 1
    if(WheelAngle < -1) WheelAngle = -1

    if(Speed > 0.01f) {
      Rotation += Speed * WheelAngle * elapsedTime
      Rotation %= (Math.PI * 2).toFloat
    }

    DebugHUD.setLogVar("Car - Rotation", Rotation)
    DebugHUD.setLogVar("Car - WheelAngle", WheelAngle)
    DebugHUD.setLogVar("Car - Speed", Speed)
  }
}
