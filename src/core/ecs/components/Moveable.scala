package ch.hevs.fastandmudry
package core.ecs.components

trait Moveable extends Speedable with Locatable with Orientable {
  private var _distance = 0f

  def Distance: Float = _distance

  def Distance_=(value: Float): Unit = {
    _distance = value
  }

  def Moving(elapsedTime: Float, factor: Float = 10f): Unit = {
    println(Coordinates)
    Coordinates.y += math.cos(Rotation).toFloat * Speed * elapsedTime * factor
    Coordinates.x += math.sin(Rotation).toFloat * Speed * elapsedTime * factor
  }
}
