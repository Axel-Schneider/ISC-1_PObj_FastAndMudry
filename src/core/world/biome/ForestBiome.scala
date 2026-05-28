package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color

import scala.util.Random

class ForestBiome extends Biome {
  private val sky: BitmapImage = new BitmapImage("data/parallax/forest/forest_sky.png")
  private val layers: Array[ParallaxLayer] = Array(
    new ParallaxLayer(new BitmapImage("data/parallax/forest/forest_mountain.png"), -400f, 0.30f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/forest/forest_back.png"), -800f, 0.20f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/forest/forest_mid.png"), -1500f, 0.3f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/forest/forest_short.png"), -2500f, 0.15f, true)
  )

  override def offRoadDecreasingFactorSpeed: Float = 0.5f
  override def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit = {
    if(isOffRoad) {
      if(Random.nextFloat()*elapsedTime < 0.01f*elapsedTime){
        println("BOOM TIRE")
        val r = Random.nextBoolean()
        if(r) car.IsLeftTirePerforated = true else car.IsRightTirePerforated = true
      }
    }
  }

  override def getRoadLineColor(): Color = Color.WHITE

  override def offRoadLowColor(): Color  = new Color( 22f / 255f,  85f / 255f,  30f / 255f, 1f)
  override def offRoadHighColor(): Color = new Color( 50f / 255f, 170f / 255f,  70f / 255f, 1f)

  override def roadLowColor(): Color  = new Color( 70f / 255f,  70f / 255f,  70f / 255f, 1f)
  override def roadHighColor(): Color = new Color(110f / 255f, 110f / 255f, 110f / 255f, 1f)

  override def skyImage(): BitmapImage = sky

  override def parallaxLayers(): Array[ParallaxLayer] = layers
}