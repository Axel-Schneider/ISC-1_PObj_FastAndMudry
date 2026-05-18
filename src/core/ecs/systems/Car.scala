package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components._

import com.badlogic.gdx.{Gdx, Input}

class Car extends AGameLoop with Orientable with Moveable with Steerable {
  override def onGameLoop(elapsedTime: Float): Unit = {
    if(Gdx.input.isKeyPressed(Input.Keys.UP))
      Speed += 2f * elapsedTime
    else
      Speed -= 1f * elapsedTime

    WheelAngle = 0
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      Rotation -= 0.7f * elapsedTime
      WheelAngle += 1
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      Rotation += 0.7f * elapsedTime
      WheelAngle -= 1
    }
  }
}
