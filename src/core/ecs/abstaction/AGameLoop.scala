package ch.hevs.fastandmudry
package core.ecs.abstaction

abstract class AGameLoop {
  def onGameLoop(elapsedTime: Float): Unit
}
