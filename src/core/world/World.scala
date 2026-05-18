package ch.hevs.fastandmudry
package core.world

import ch.hevs.fastandmudry.core.ecs.components.AGameLoop
import ch.hevs.fastandmudry.core.ecs.systems.{Car, Track}
import com.badlogic.gdx.{Gdx, Input}

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
