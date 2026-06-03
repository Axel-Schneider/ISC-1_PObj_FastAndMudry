package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.components.Locatable

trait CircleCollision extends Collisional {
  protected var Size = 10;
  override def CheckCollision(location: Locatable): Boolean = {
    location.Coordinates.dst2(Coordinates) <= Size
  }
}
