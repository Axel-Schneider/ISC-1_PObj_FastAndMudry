package ch.hevs.fastandmudry
package core.ecs.components

trait Moveable extends Locatable with Orientable {
  private var _speed = 0f
  private var _maxSpeed = 1f

  def Speed: Float = _speed
  def MaxSpeed: Float = _maxSpeed

  def Speed_=(value: Float): Unit = {
    _speed = math.max(0f, math.min(value, _maxSpeed))
  }
  def MaxSpeed_=(value: Float): Unit = {
    _maxSpeed = value
    if (_speed > _maxSpeed) _speed = _maxSpeed
  }

  def Moving(elapsedTime: Float, factor: Float = 10f): Unit = {
    Coordinates.y += math.cos(Rotation).toFloat * Speed * elapsedTime * factor
    Coordinates.x += math.sin(Rotation).toFloat * Speed * elapsedTime * factor
  }
}
