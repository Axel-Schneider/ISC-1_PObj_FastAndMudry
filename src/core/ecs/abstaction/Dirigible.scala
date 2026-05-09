package ch.hevs.fastandmudry
package core.ecs.abstaction

trait Dirigible {
  private var _direction: Float = 0f

  def Direction: Float = _direction

  def Direction_=(value: Float): Unit = {
    _direction = value
  }
}
