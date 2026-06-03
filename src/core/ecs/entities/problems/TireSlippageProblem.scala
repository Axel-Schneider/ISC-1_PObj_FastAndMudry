package ch.hevs.fastandmudry.core.ecs.entities.problems

import ch.hevs.fastandmudry.core.ecs.components.problems.Problem
import ch.hevs.fastandmudry.core.ecs.systems.Car
import com.badlogic.gdx.Input

class TireSlippageProblem extends Problem {

  override def impactCar(elapsedTime: Float, car: Car): Unit = {
    if(IsDefected) {
      car.rightKey = Input.Keys.LEFT
      car.leftKey = Input.Keys.RIGHT
    } else {
      car.rightKey = Input.Keys.RIGHT
      car.leftKey = Input.Keys.LEFT
    }
  }
}
