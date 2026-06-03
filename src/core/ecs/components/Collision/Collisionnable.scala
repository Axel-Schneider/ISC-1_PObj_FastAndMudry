package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.components.Locatable

trait Collisionnable extends Locatable {
  def onCollision(): Unit
  def CheckCollision(location: Locatable): Boolean
}
