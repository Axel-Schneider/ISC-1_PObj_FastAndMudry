package ch.hevs.fastandmudry.core.ecs.components.problems

import ch.hevs.fastandmudry.core.ecs.systems.Car

trait Problem {
  private var _isBroken = false;
  def IsBroken: Boolean = _isBroken
  def IsBroken_=(value: Boolean): Unit = { _isBroken = value }

  def impactCar(elapsedTime: Float, car: Car): Unit

  def reset(): Unit = {
    IsBroken = false;
  }
}
