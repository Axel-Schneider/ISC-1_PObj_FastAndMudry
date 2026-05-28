package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color

trait Biome {
  // The factor decreasing the car speed when the car is off the road
  def offRoadDecreasingFactorSpeed: Float
  def getRoadColor(): Color
  def getRoadLineColor(): Color
  def getOffRoadColor(): Color
  def getOffRoadColor(x: Int, y: Int): Color = getOffRoadColor()
  def prepareOffRoadTexture(width: Int, height: Int): Unit = ()
  def parallaxLayers(): Array[ParallaxLayer]
  def skyImage(): BitmapImage

  // Update the car physics (tire explosion, motor overheat, ...)
  def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit
}
