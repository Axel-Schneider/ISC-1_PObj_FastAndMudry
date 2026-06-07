package ch.hevs.fastandmudry.core.ecs.entities.problems

import ch.hevs.fastandmudry.core.ecs.components
import ch.hevs.fastandmudry.core.ecs.components.Axle.Axle
import ch.hevs.fastandmudry.core.ecs.components.Side.Side
import ch.hevs.fastandmudry.core.ecs.components.problems.{Problem, Reparable}
import ch.hevs.fastandmudry.core.ecs.systems.Car
import ch.hevs.fastandmudry.ui.hud.DebugHUD
import ch.hevs.fastandmudry.utils.Constant.GAME.CAR.FACTOR

import scala.util.Random

class TireProblem(val Side: Side, val Axle: Axle) extends Problem with Reparable {
  Title = s"$Axle $Side tire"
  ReparationPrice = 25
  IconPath = "data/images/problems/wheel.png"

  override def impactCar(elapsedTime: Float, car: Car): Unit = {
    DebugHUD.setLogVar(s"Tire - ${Axle} ${Side} Perforated", IsDefected)

    if(IsDefected) {
      if(Axle == components.Axle.Front) {
        if((Side == components.Side.Right && car.WheelAngle >= 0) || (Side == components.Side.Left && car.WheelAngle <= 0)){
          car.WheelAngle += (if(Side == components.Side.Right) 1 else -1) * FACTOR.WHEEL_ROTATION * elapsedTime * 0.1f
          car.IsSteeringWheelReturnEnable = false
        }
      } else {
        car.MaxSpeed *= 0.5f
      }
    }
  }

  def updateBroken(prob: Float): Unit = {
    if(Random.nextFloat() < prob) IsDefected = true
  }
}
