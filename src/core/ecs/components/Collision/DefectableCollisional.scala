package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.systems.Car

trait DefectableCollisional {
  def defectCar(obj: Car): Unit
}
