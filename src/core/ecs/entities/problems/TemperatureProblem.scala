package ch.hevs.fastandmudry.core.ecs.entities.problems

import ch.hevs.fastandmudry.core.ecs.components.problems.{Critical, Problem}
import ch.hevs.fastandmudry.core.ecs.systems.Car
import ch.hevs.fastandmudry.utils.Constant.GAME.CAR

class TemperatureProblem(val Car: Car) extends Problem with Critical {
  override def impactCar(elapsedTime: Float, car: Car): Unit = {
  }

  def updateBroken(elapsedTime: Float, factor: Float): Unit = {
    Car.Temperature += factor * elapsedTime

    if(Car.Temperature <= CAR.FACTOR.MIN_TEMPERATURE || Car.Temperature >= CAR.FACTOR.MAX_TEMPERATURE) IsBroken = true
  }
}
