package ch.hevs.fastandmudry
package core.ecs.components

trait Positionable {
  private var _roadPosition: Float = 0f

  def RoadPosition: Float = _roadPosition

  def RoadPosition_=(value: Float): Unit = {
    _roadPosition = value
  }
}
