package ch.hevs.fastandmudry
package core.ecs.abstaction

trait Curvable {
  private var _curvature: Float = 0f;

  def Curvature: Float = _curvature

  def Curvature_=(value: Float): Unit = {
    _curvature = value
  }
}
