package ch.hevs.fastandmudry
package core.ecs.components

trait Orientable {
  private var _rotation: Float = 0f;

  def Rotation: Float = _rotation

  def Rotation_=(value: Float): Unit = {
    _rotation = value
  }
}
