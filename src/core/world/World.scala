package ch.hevs.fastandmudry
package core.world

import core.ecs.components.AGameLoop
import core.ecs.systems.Car
import core.ecs.systems.track.Track

import ch.hevs.fastandmudry.utils.Constant.GAME

class World private () extends AGameLoop {
  val CAR: Car = new Car
  val TRACK: Track = new Track(CAR)

  override def onGameLoop(elapsedTime: Float): Unit = {
    CAR.onGameLoop(elapsedTime)
    CAR.Moving(elapsedTime, GAME.CAR.FACTOR.SPEED)

    TRACK.onGameLoop(elapsedTime)
  }
}

object World {
  private lazy val _Instance: World = new World

  def INSTANCE: World = _Instance
}
