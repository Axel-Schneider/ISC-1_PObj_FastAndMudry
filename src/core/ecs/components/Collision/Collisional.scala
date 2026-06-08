package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.audio.AudioManager
import ch.hevs.fastandmudry.core.ecs.components.{Locatable, Moveable}
import ch.hevs.fastandmudry.core.ecs.systems.Car

trait Collisional extends Locatable with DefectableCollisional {
  def onCollision(obj: Car): Unit = {
    obj.Speed = 0
    AudioManager.playCollision()
    defectCar(obj)
  }
  def CheckCollision(location: Locatable): Boolean
}
