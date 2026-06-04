package ch.hevs.fastandmudry.core.ecs.entities.problems

import ch.hevs.fastandmudry.core.ecs.components.problems.{Problem, Reparable}
import ch.hevs.fastandmudry.core.ecs.systems.Car

class ChassisProblem extends Problem with Reparable {
  Title = "Chassis"
  ReparationPrice = 100
  IconPath = "data/images/problems/chassis.png"

  private var hasChanged = false
  override def impactCar(elapsedTime: Float, car: Car): Unit = {
    if(IsDefected && !hasChanged) {
      car.MaxSpeed -= 10f
      hasChanged = true
    }
  }
}
