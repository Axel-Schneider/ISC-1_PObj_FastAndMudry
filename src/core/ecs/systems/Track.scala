package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components.AGameLoop

class Track(private val Car: Car) extends AGameLoop {

  def generateNewMap(): Unit = {
    // TO DO : Generating a random map with border
    // Warning ! Generate a map object and not only a map image !
  }

  def getMapComponent(): Unit = {
    // TO DO : Return a random map in a chunk
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    // Checkup game interaction between car and track
  }
}
