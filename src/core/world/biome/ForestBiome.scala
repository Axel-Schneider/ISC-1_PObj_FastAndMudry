package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car

import com.badlogic.gdx.graphics.Color

class ForestBiome extends Biome {
  override def offRoadDecreasingFactorSpeed: Float = 0.5f
  override def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit = {

  }

  override def getRoadColor(): Color = Color.GRAY

  override def getRoadLineColor(): Color = Color.YELLOW

  override def getOffRoadColor(): Color = Color.GREEN

  override def getBackgroundPath(): Array[String] = {
    Array(
      "data/parallax/forest/forest_sky.png",
      "data/parallax/forest/forest_mountain.png",
      "data/parallax/forest/forest_back.png",
      "data/parallax/forest/forest_mid.png",
      "data/parallax/forest/forest_short.png"
    )
  }
}