package ch.hevs.fastandmudry
package core.ecs.components

trait Distanceable extends Speedable {
  private var _distance = 0f

  def Distance: Float = _distance

  def Distance_=(value: Float): Unit = {
    _distance = value
  }

  def Moving(elapsedTime: Float, factor: Float = 1): Unit = {
    _distance += Speed * elapsedTime * factor
  }
}
