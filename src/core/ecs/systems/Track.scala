package ch.hevs.fastandmudry
package core.ecs.systems

import core.ecs.components.{AGameLoop, Curvable, Distanceable}

import scala.collection.mutable.ArrayBuffer

class Track(private val Car: Car) extends AGameLoop with Curvable with Distanceable {
  private var trackVector: ArrayBuffer[(Float, Float)] = ArrayBuffer[(Float, Float)]()
  private var _targetCurvature: Float = 0f;
  private var _currentCurvature = 0f

  def TargetCurvature: Float = _targetCurvature
  def CurrentCurvature: Float = _currentCurvature

  trackVector.append((0f, 10f))
  trackVector.append((0f, 200f))
  trackVector.append((1f, 200f))
  trackVector.append((0f, 400f))
  trackVector.append((-1f, 200f))
  trackVector.append((0f, 200f))
  trackVector.append((-1f, 200f))
  trackVector.append((1f, 200f))
  trackVector.append((0f, 200f))

  trackVector.foreach(t => Distance += t._2)

  def getCurrentTrack: (Float, Float) = {
    var realDistance = Car.Distance % Distance
    var offset = 0f
    var trackSection = 0

    while (trackSection < trackVector.size && offset <= realDistance) {
      offset += trackVector(trackSection)._2
      trackSection += 1
    }
    trackVector(trackSection-1)
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    val targetCurvature = getCurrentTrack._1
    val trackCurvatureDiff = (targetCurvature - _currentCurvature) * elapsedTime * Car.Speed * 0.5f
    _currentCurvature += trackCurvatureDiff
    Curvature += _currentCurvature * elapsedTime * Car.Speed
  }
}
