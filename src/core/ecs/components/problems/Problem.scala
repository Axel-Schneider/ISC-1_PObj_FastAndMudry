package ch.hevs.fastandmudry.core.ecs.components.problems

import ch.hevs.fastandmudry.core.ecs.systems.Car

trait Problem {
  private var _isDefected = false;
  def IsDefected: Boolean = _isDefected
  def IsDefected_=(value: Boolean): Unit = { _isDefected = value }

  def impactCar(elapsedTime: Float, car: Car): Unit

  def reset(): Unit = {
    IsDefected = false;
  }
}
