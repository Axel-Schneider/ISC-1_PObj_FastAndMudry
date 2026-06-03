package ch.hevs.fastandmudry.core.ecs.entities.problems

import ch.hevs.fastandmudry.core.ecs.components.problems.{Problem, Reparable}
import ch.hevs.fastandmudry.core.ecs.systems.Car

class ChassisProblem extends Problem with Reparable {
  var hasChanged = false
  override def impactCar(elapsedTime: Float, car: Car): Unit = {
    if(IsBroken && !hasChanged) {
      car.MaxSpeed -= 10f
      hasChanged = true
    }
  }
}
