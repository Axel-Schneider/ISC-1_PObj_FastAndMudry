package ch.hevs.fastandmudry
package render

import core.world.World

object Data {
  object Game {
    def Distance: Float = World.INSTANCE.CAR.Distance
    def Curvature: Float = World.INSTANCE.CAR.Rotation
  }
}
