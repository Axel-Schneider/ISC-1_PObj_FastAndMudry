package ch.hevs.fastandmudry
package core.ecs.components

import core.ecs.abstaction.{Curvable, Distanceable}

import scala.collection.mutable.ArrayBuffer

class Track extends Curvable with Distanceable {
  private var trackVector: ArrayBuffer[(Float, Float)] = ArrayBuffer[(Float, Float)]()

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

  def getCurrentTrack(currentDistance: Float): (Float, Float) = {
    var realDistance = currentDistance % Distance
    var offset = 0f
    var trackSection = 0

    while (trackSection < trackVector.size && offset <= realDistance) {
      offset += trackVector(trackSection)._2
      trackSection += 1
    }
    trackVector(trackSection-1)
  }
}
