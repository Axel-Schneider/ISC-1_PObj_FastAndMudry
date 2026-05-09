package ch.hevs.fastandmudry
package core.ecs.abstaction

trait Speedable {
  private var _speed = 0f
  private var _maxSpeed = 1f

  def Speed: Float = _speed
  def MaxSpeed: Float = _maxSpeed

  def Speed_=(value: Float): Unit = {
    if(value > _maxSpeed || value < 0) return
    _speed = value
  }
  def MaxSpeed_=(value: Float): Unit = {
    _maxSpeed = value
  }
}
