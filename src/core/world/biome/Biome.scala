package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color

trait Biome {
  private var offRoadNoiseField: Array[Array[Color]] = _

  // The factor decreasing the car speed when the car is off the road
  def offRoadDecreasingFactorSpeed: Float
  def getRoadColor(): Color
  def getRoadLineColor(): Color
  def offRoadLowColor(): Color
  def offRoadHighColor(): Color
  def offRoadNoiseCell(): Int = 24

  def getOffRoadColor(x: Int, y: Int): Color = offRoadNoiseField(x)(y)
  def prepareOffRoadTexture(width: Int, height: Int): Unit = {
    offRoadNoiseField = BiomeTexture.generateNoiseField(
      width, height, offRoadNoiseCell(), offRoadLowColor(), offRoadHighColor()
    )
  }

  def parallaxLayers(): Array[ParallaxLayer]
  def skyImage(): BitmapImage

  // Update the car physics (tire explosion, motor overheat, ...)
  def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit
}
