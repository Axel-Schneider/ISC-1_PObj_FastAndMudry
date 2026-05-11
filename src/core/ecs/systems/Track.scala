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


  for(_ <- 0 to 20) {
    trackVector.append(generateRandomVector())
  }

  trackVector.foreach(t => Distance += t._2)

  def getCurrentTrack: (Float, Float) = {
    getTrackAt(Car.Distance)
  }

  def getTrackAt(d: Float): (Float, Float) = {
    var realDistance = d % Distance
    var offset = 0f
    var trackSection = 0

    while (trackSection < trackVector.size && offset <= realDistance) {
      offset += trackVector(trackSection)._2
      trackSection += 1
    }
    trackVector(trackSection-1)
  }

  private def generateRandomVector(): (Float, Float) = {
    (math.random().toFloat*2f-1f, math.random().toFloat*800f+200f)
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    val diff = Car.Curvature - Curvature
    if(diff > 1) Car.Curvature = Curvature + 1
    else if (diff < -1) Car.Curvature = Curvature -1
    val targetCurvature = getCurrentTrack._1
    val trackCurvatureDiff = (targetCurvature - _currentCurvature) * elapsedTime * Car.Speed * 0.5f

    _currentCurvature += trackCurvatureDiff
    Curvature += _currentCurvature * elapsedTime * Car.Speed
  }
}
