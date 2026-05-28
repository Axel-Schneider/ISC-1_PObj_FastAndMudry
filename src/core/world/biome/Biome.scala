package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car
import utils.Constant.MapTexture

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color

trait Biome {
  private var offRoadNoiseField: Array[Array[Color]] = _
  private var roadNoiseField: Array[Array[Color]] = _
  private var shoulderNoiseField: Array[Array[Color]] = _
  private var shoulderJitterField: Array[Array[Float]] = _

  // The factor decreasing the car speed when the car is off the road
  def offRoadDecreasingFactorSpeed: Float
  def getRoadLineColor(): Color

  def offRoadLowColor(): Color
  def offRoadHighColor(): Color
  def offRoadNoiseCell(): Int = 24

  def roadLowColor(): Color
  def roadHighColor(): Color
  def roadNoiseCell(): Int = 4

  def shoulderLowColor(): Color
  def shoulderHighColor(): Color
  def shoulderNoiseCell(): Int = 16

  def getOffRoadColor(x: Int, y: Int): Color = offRoadNoiseField(x)(y)
  def getRoadColor(x: Int, y: Int): Color = roadNoiseField(x)(y)
  def getShoulderColor(x: Int, y: Int): Color = shoulderNoiseField(x)(y)

  def getShoulderJitter(x: Int, y: Int): Float =
    (shoulderJitterField(x)(y) * 2f - 1f) * MapTexture.SHOULDER_JITTER

  def prepareTextures(width: Int, height: Int): Unit = {
    offRoadNoiseField = BiomeTexture.generateNoiseField(
      width, height, offRoadNoiseCell(), offRoadLowColor(), offRoadHighColor()
    )
    roadNoiseField = BiomeTexture.generateNoiseField(
      width, height, roadNoiseCell(), roadLowColor(), roadHighColor()
    )
    shoulderNoiseField = BiomeTexture.generateNoiseField(
      width, height, shoulderNoiseCell(), shoulderLowColor(), shoulderHighColor()
    )
    shoulderJitterField = BiomeTexture.generateScalarField(
      width, height, MapTexture.SHOULDER_JITTER_NOISE_CELL
    )
  }

  def parallaxLayers(): Array[ParallaxLayer]
  def skyImage(): BitmapImage

  // Update the car physics (tire explosion, motor overheat, ...)
  def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit
}
