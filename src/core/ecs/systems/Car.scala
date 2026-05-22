package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components._

import com.badlogic.gdx.{Gdx, Input}

// TODO: Replace the temporary car constants
object Car {
  val MAX_SPEED: Float    = 15f
  val ACCELERATION: Float = 12f
  val DECELERATION: Float = 4f
  val TURN_RATE: Float    = 0.7f
}

class Car extends AGameLoop with Orientable with Moveable with Steerable {
  MaxSpeed = Car.MAX_SPEED

  override def onGameLoop(elapsedTime: Float): Unit = {
    if (Gdx.input.isKeyPressed(Input.Keys.UP))
      Speed += Car.ACCELERATION * elapsedTime
    else
      Speed -= Car.DECELERATION * elapsedTime

    WheelAngle = 0
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      Rotation -= Car.TURN_RATE * elapsedTime
      WheelAngle += 1
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      Rotation += Car.TURN_RATE * elapsedTime
      WheelAngle -= 1
    }
  }
}
