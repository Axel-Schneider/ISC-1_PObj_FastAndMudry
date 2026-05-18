package ch.hevs.fastandmudry
package core.world

import core.ecs.components.AGameLoop
import core.ecs.systems.{Car, Track}

class World private () extends AGameLoop {
  val CAR: Car = new Car
  val TRACK: Track = new Track(CAR)

  override def onGameLoop(elapsedTime: Float): Unit = {
    CAR.onGameLoop(elapsedTime)
    CAR.Moving(elapsedTime)

    TRACK.onGameLoop(elapsedTime)
  }
}

object World {
  private lazy val _Instance: World = new World

  def INSTANCE: World = _Instance
}
