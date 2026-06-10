package ch.hevs.fastandmudry
package core.ecs.systems

import ch.hevs.fastandmudry.core.ecs.components._
import ch.hevs.fastandmudry.core.ecs.components.problems.{Critical, Problem, Reparable}
import ch.hevs.fastandmudry.core.ecs.entities.problems.{ChassisProblem, TemperatureProblem, TireProblem, TireSlippageProblem}
import ch.hevs.fastandmudry.ui.hud.DebugHUD
import ch.hevs.fastandmudry.utils.Constant.GAME.CAR.FACTOR
import com.badlogic.gdx.{Gdx, Input}

import scala.collection.mutable.ArrayBuffer

class Car extends AGameLoop with Orientable with Moveable with Steerable with Temperable with GodMode {
  MaxSpeed = FACTOR.MAX_SPEED
  var isBroken: Boolean = false

  val FrontLeftTire = new TireProblem(Side.Left, Axle.Front)
  val BackLeftTire = new TireProblem(Side.Left, Axle.Rear)
  val FrontRightTire = new TireProblem(Side.Right, Axle.Front)
  val BackRightTire = new TireProblem(Side.Right, Axle.Rear)
  val TemperatureProblem = new TemperatureProblem(this)
  val TireSlippage = new TireSlippageProblem
  val ChassisProblem = new ChassisProblem

  private val Problems = ArrayBuffer[Problem](
    FrontLeftTire,
    BackLeftTire,
    FrontRightTire,
    BackRightTire,
    TemperatureProblem,
    TireSlippage,
    ChassisProblem
  )

  def getReparableProblems: ArrayBuffer[Reparable] = Problems.collect[Reparable] {
    case r: Reparable => r
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    checkGodMode()
    IsSteeringWheelReturnEnable = true;
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      Speed += FACTOR.ACCELERATION * elapsedTime * (if(IsGoingBackward) 4f else 1f)
      if(Speed >= 0) IsGoingBackward = false
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      IsGoingBackward = true
      Speed -= FACTOR.ACCELERATION * elapsedTime * (if(Speed > 0) 4f else 1f)
    }
    else {
      Speed -= (if(IsGoingBackward) -1 else 1) * FACTOR.DECELERATION * elapsedTime
      if(Speed >= 0) IsGoingBackward = false
    }

    if (Gdx.input.isKeyPressed(leftKey)) {
      WheelAngle -= FACTOR.WHEEL_ROTATION * elapsedTime
      IsSteeringWheelReturnEnable = false
    }
    if (Gdx.input.isKeyPressed(rightKey)) {
      WheelAngle += FACTOR.WHEEL_ROTATION * elapsedTime
      IsSteeringWheelReturnEnable = false
    }

    if(!IsGodModeEnable) Problems.foreach(_.impactCar(elapsedTime, this))

    if(IsSteeringWheelReturnEnable) {
      WheelAngle *= Math.pow(FACTOR.WHEEL_RETURN, elapsedTime.toDouble).toFloat
      if (Math.abs(WheelAngle) < 0.001f) WheelAngle = 0f
    }

    if(WheelAngle > FACTOR.WHEEL_MAX_ANGLE) WheelAngle = FACTOR.WHEEL_MAX_ANGLE
    if(WheelAngle < -FACTOR.WHEEL_MAX_ANGLE) WheelAngle = -FACTOR.WHEEL_MAX_ANGLE

    if (Math.abs(Speed) > 0.01f) {
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
    isBroken = false
  }

  def checkCarState(): Boolean = {
    Problems.filter(_.isInstanceOf[Critical]).exists(_.IsDefected)
  }
}
