package ch.hevs.fastandmudry
package core.ecs.components

trait Moveable extends Locatable with Orientable {
  private var _speed = 0f
  private var _maxSpeed = 1f

  def Speed: Float = _speed
  def MaxSpeed: Float = _maxSpeed

  def Speed_=(value: Float): Unit = {
    _speed =
      if(value > _maxSpeed) _maxSpeed
      else if(value < 0) 0
      else value
  }
  def MaxSpeed_=(value: Float): Unit = {
    _maxSpeed = value
  }

  def Moving(elapsedTime: Float, factor: Float = 10f): Unit = {
    Coordinates.y += math.cos(Rotation).toFloat * Speed * elapsedTime * factor
    Coordinates.x += math.sin(Rotation).toFloat * Speed * elapsedTime * factor
  }
}
