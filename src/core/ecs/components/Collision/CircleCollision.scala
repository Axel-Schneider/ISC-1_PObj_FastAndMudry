package ch.hevs.fastandmudry.core.ecs.components.Collision

import ch.hevs.fastandmudry.core.ecs.components.Locatable

trait CircleCollision extends Collisionnable {
  protected var Size = 10;
  override def CheckCollision(location: Locatable): Boolean = {
    println(s"Dst2 ${location.Coordinates} -> ${Coordinates}  =  ${location.Coordinates.dst2(Coordinates)}")
    location.Coordinates.dst2(Coordinates) <= Size
  }
}
