package ch.hevs.fastandmudry
package core.ecs.components

trait Steerable {
  private var _wheelAngle: Float = 0f

  def WheelAngle: Float = _wheelAngle

  def WheelAngle_=(value: Float): Unit = {
    _wheelAngle = value
  }
}
