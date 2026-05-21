package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components.AGameLoop

import com.badlogic.gdx.math.Vector2

class Track(private val Car: Car) extends AGameLoop {

  def generateNewMap(): Unit = {
    // TO DO : Generating a random map with border
    // Warning ! Generate a map object and not only a map image !
    val gen = new MapGenerator(new Vector2(0f, 100f), new Vector2(1000f, 100f), 8)
  }

  def getMapComponent(): Unit = {
    // TO DO : Return a random map in a chunk
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    // Checkup game interaction between car and track
    generateNewMap()
  }
}
