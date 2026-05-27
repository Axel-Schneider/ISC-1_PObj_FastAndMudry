package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
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

  override def getRoadColor(): Color = new Color(0xd9d9ffff)

  override def getRoadLineColor(): Color = Color.WHITE

  override def getOffRoadColor(): Color = new Color(0xe6ffffff)

  override def parallaxLayers(): Array[ParallaxLayer] = layers

  override def skyImage(): BitmapImage = sky

  override def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit = {

  }
}
