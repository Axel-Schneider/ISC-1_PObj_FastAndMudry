package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color

class DesertBiome extends Biome {
  private val sky: BitmapImage = new BitmapImage("data/parallax/desert/desert_sky.png")
  private val layers: Array[ParallaxLayer] = Array(
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_mountain.png"), -400f, 0.30f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_moon.png"), -800f, 0.20f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_dunemid.png"), -1500f, 0.3f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_dunefront.png"), -2500f, 0.15f, true)
  )

  override def offRoadDecreasingFactorSpeed: Float = 0.2f

  override def getRoadColor(): Color = Color.GRAY

  override def getRoadLineColor(): Color = Color.YELLOW

  override def getOffRoadColor(): Color = new Color(0xffd966ff)

  override def parallaxLayers(): Array[ParallaxLayer] = layers

  override def skyImage(): BitmapImage = sky

  override def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit = {
    if(isOffRoad) {
      car.Temperature += 4f * elapsedTime
    } else {

    }
  }
}
