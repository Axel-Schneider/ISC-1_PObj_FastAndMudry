package ch.hevs.fastandmudry
package core.ecs.components

abstract class AGameLoop {
  def onGameLoop(elapsedTime: Float): Unit
}
