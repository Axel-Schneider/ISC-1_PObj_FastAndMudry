package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.components.Locatable

trait PassThoughtCollision extends Collisionnable {
  override def CheckCollision(location: Locatable): Boolean = false
}
