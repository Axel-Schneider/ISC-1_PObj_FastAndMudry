package ch.hevs.fastandmudry
package core.world

import ch.hevs.fastandmudry.core.ecs.abstaction.AGameLoop
import ch.hevs.fastandmudry.core.ecs.components.{Car, Track}
import com.badlogic.gdx.{Gdx, Input}

class World extends AGameLoop {
  val CAR: Car = new Car
  val TRACK: Track = new Track(CAR)

  override def onGameLoop(elapsedTime: Float): Unit = {
    CAR.onGameLoop(elapsedTime)
    TRACK.onGameLoop(elapsedTime)

    if(math.abs(CAR.Curvature - TRACK.Curvature) >= 0.8f)
      CAR.Speed -= 5.0f * elapsedTime

    CAR.Moving(elapsedTime, 100)
    CAR.RoadPosition = CAR.Curvature - TRACK.Curvature
  }
}
