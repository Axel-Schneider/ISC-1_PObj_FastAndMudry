package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.components.{Locatable, Moveable}

trait PassThoughtCollision extends Collisional {
  override def CheckCollision(location: Locatable): Boolean = false
}
