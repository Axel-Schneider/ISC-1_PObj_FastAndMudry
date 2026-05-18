package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components.{AGameLoop, Orientable, Moveable}

import scala.collection.mutable.ArrayBuffer

class Track(private val Car: Car) extends AGameLoop {

  override def onGameLoop(elapsedTime: Float): Unit = {
    // Checkup game interaction between car and track
  }
}
