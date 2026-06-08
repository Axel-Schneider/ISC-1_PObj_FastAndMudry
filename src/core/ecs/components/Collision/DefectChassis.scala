package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.systems.Car

trait DefectChassis extends DefectableCollisional {
  override def defectCar(obj: Car): Unit = {
    obj.ChassisProblem.IsDefected = true
  }
}
