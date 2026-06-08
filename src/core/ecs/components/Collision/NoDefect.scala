package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.systems.Car

trait NoDefect extends DefectableCollisional {
  override def defectCar(obj: Car): Unit = {}
}
