package ch.hevs.fastandmudry
package core.ecs.components

trait Moveable extends Locatable with Orientable {
  private var _speed = 0f
  private var _maxSpeed = 1f
  private var _isGoingBackward: Boolean = false

  private def MinSpeed = -_maxSpeed/4f

  def Speed: Float = _speed
  def MaxSpeed: Float = _maxSpeed

  def Speed_=(value: Float): Unit = {
    _speed =
      if(value > _maxSpeed) _maxSpeed
      else if(IsGoingBackward && value < MinSpeed) MinSpeed
      else if(!IsGoingBackward && value < 0) 0
      else value
  }
  def MaxSpeed_=(value: Float): Unit = {
    _maxSpeed = value
    if (_speed > _maxSpeed) _speed = _maxSpeed
  }

  def IsGoingBackward: Boolean = _isGoingBackward
  def IsGoingBackward_=(value: Boolean): Unit = {
    _isGoingBackward = value
  }

  def Moving(elapsedTime: Float, factor: Float = 10f): Unit = {
    Coordinates.y += math.cos(Rotation).toFloat * Speed * elapsedTime * factor
    Coordinates.x += math.sin(Rotation).toFloat * Speed * elapsedTime * factor
  }
}
