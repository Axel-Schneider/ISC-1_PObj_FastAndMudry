package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components._

import com.badlogic.gdx.{Gdx, Input}

class Car extends AGameLoop with Curvable with Distanceable with Speedable with Positionable with Dirigible {
  private val ACCELERATION_RATIO = 0.2f
  override def onGameLoop(elapsedTime: Float): Unit = {
    if(Gdx.input.isKeyPressed(Input.Keys.UP))
      Speed += 2f * elapsedTime * ACCELERATION_RATIO
    else
      Speed -= 1f * elapsedTime * ACCELERATION_RATIO

    Direction = 0
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      Curvature -= 0.7f * elapsedTime
      Direction += 1
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      Curvature += 0.7f * elapsedTime
      Direction -= 1
    }
  }
}
