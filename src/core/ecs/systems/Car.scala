package ch.hevs.fastandmudry
package core.ecs.systems

import ch.hevs.fastandmudry.core.ecs.components.problems.{Problem, Tire}
import core.ecs.components._
import com.badlogic.gdx.{Gdx, Input}
import utils.Constant.GAME.CAR.FACTOR
import ui.hud.DebugHUD
import ch.hevs.fastandmudry.utils.Constant.GAME.CAR

import scala.collection.mutable.ArrayBuffer

class Car extends AGameLoop with Orientable with Moveable with Steerable with Temperable with HasTires {
  MaxSpeed = FACTOR.MAX_SPEED
  var isBroken: Boolean = false

  val FrontLeftTire = new Tire(Side.Left, Axle.Front)
  val BackLeftTire = new Tire(Side.Left, Axle.Rear)
  val FrontRightTire = new Tire(Side.Right, Axle.Front)
  val BackRightTire = new Tire(Side.Right, Axle.Rear)

  private val Problems = ArrayBuffer[Problem](
    FrontLeftTire,
    BackLeftTire,
    FrontRightTire,
    BackRightTire
  )

  override def onGameLoop(elapsedTime: Float): Unit = {
    IsSteeringWheelReturnEnable = true;
    if (Gdx.input.isKeyPressed(Input.Keys.UP))
      Speed += FACTOR.ACCELERATION * elapsedTime
    else
      Speed -= FACTOR.DECELERATION * elapsedTime

    if (Gdx.input.isKeyPressed(leftKey)) {
      WheelAngle -= FACTOR.WHEEL_ROTATION * elapsedTime
      IsSteeringWheelReturnEnable = false
    }
    if (Gdx.input.isKeyPressed(rightKey)) {
      WheelAngle += FACTOR.WHEEL_ROTATION * elapsedTime
      IsSteeringWheelReturnEnable = false
    }

    Problems.foreach(_.impactCar(elapsedTime, this))

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
  }

  def reset(): Unit = {
    MaxSpeed = FACTOR.MAX_SPEED
    Speed = 0f
    WheelAngle = 0f
    Temperature = 0f
    Problems.foreach(_.reset())
    isBroken = false
  }

  def checkCarState(): Boolean = {
    if (Temperature <= CAR.FACTOR.MIN_TEMPERATURE || Temperature >= CAR.FACTOR.MAX_TEMPERATURE) {
      return true
    }
    false
  }
}
