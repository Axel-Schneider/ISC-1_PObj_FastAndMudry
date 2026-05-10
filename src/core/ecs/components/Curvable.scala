package ch.hevs.fastandmudry
package core.ecs.components

trait Curvable {
  private var _curvature: Float = 0f;

  def Curvature: Float = _curvature

  def Curvature_=(value: Float): Unit = {
    _curvature = value
  }
}
