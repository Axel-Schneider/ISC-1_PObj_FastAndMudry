package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components.AGameLoop

class Track(private val Car: Car) extends AGameLoop {

  override def onGameLoop(elapsedTime: Float): Unit = {
    // Checkup game interaction between car and track
  }
}
