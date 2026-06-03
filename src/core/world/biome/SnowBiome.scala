package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color

class SnowBiome extends Biome {
  private val sky: BitmapImage = new BitmapImage("data/parallax/skies/sky_sky.png")
  private val layers: Array[ParallaxLayer] = Array(
    new ParallaxLayer(new BitmapImage("data/parallax/skies/sky_back_mountain.png"), -400f, 0.30f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/skies/sky_cloud_floor.png"), -800f, 0.20f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/skies/sky_front_mountain.png"), -1500f, 0.3f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/skies/sky_front_cloud.png"), -2500f, 0.15f, true)
  )

  override def offRoadDecreasingFactorSpeed: Float = 0.7f

  override def getRoadLineColor(): Color = Color.YELLOW

  override def offRoadLowColor(): Color = new Color(180f / 255f, 205f / 255f, 240f / 255f, 1f)
  override def offRoadHighColor(): Color = new Color(255f / 255f, 255f / 255f, 255f / 255f, 1f)

  override def roadLowColor(): Color = new Color(90f / 255f, 95f / 255f, 110f / 255f, 1f)
  override def roadHighColor(): Color = new Color(200f / 255f, 210f / 255f, 230f / 255f, 1f)

  override def shoulderLowColor(): Color = new Color(115f / 255f, 130f / 255f, 150f / 255f, 1f)
  override def shoulderHighColor(): Color = new Color(200f / 255f, 220f / 255f, 230f / 255f, 1f)

  override def parallaxLayers(): Array[ParallaxLayer] = layers

  override def skyImage(): BitmapImage = sky

  override def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit = {
    if(isOffRoad) car.TemperatureProblem.updateBroken(elapsedTime, -5f)
    car.TireSlippage.IsBroken = isOffRoad
  }
}
