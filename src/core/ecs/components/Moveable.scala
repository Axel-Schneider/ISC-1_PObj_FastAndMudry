package ch.hevs.fastandmudry
package core.ecs.components

trait Moveable extends Locatable with Orientable {
  private var _speed = 0f
  private var _maxSpeed = 1f
  private var _canGoForward: Boolean = true
  private var _canGoBackward: Boolean = true

  def CanGoForward: Boolean = _canGoForward
  def CanGoForward_=(value: Boolean): Unit = {
    _canGoForward = value
  }

  def CanGoBackward: Boolean = _canGoBackward
  def CanGoBackward_=(value: Boolean): Unit = {
    _canGoBackward = value
  }

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
    if (_speed > _maxSpeed) _speed = _maxSpeed
  }

  def Moving(elapsedTime: Float, factor: Float = 10f): Unit = {
    Coordinates.y += math.cos(Rotation).toFloat * Speed * elapsedTime * factor
    Coordinates.x += math.sin(Rotation).toFloat * Speed * elapsedTime * factor
  }
}
