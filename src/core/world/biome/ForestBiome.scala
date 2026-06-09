package ch.hevs.fastandmudry
package core.world.biome

import core.audio.MusicTrack
import ch.hevs.fastandmudry.core.ecs.entities.Item.forest.{SimpleBush, SimpleRock, SimpleTree}
import ch.hevs.fastandmudry.core.ecs.entities.Item.AItem
import ch.hevs.fastandmudry.core.ecs.systems.track.TrackGeometry
import ch.hevs.fastandmudry.utils.Constant.MapTexture
import core.ecs.systems.Car
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color

import scala.util.Random

class ForestBiome extends Biome {
  override def musicTrack: MusicTrack = MusicTrack.Forest

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
      car.FrontLeftTire.updateBroken(0.005f)
      car.BackLeftTire.updateBroken(0.005f)
      car.FrontRightTire.updateBroken(0.005f)
      car.BackRightTire.updateBroken(0.005f)
    }
  }

  override def getRoadLineColor(): Color = Color.WHITE

  override def offRoadLowColor(): Color = new Color(22f / 255f, 85f / 255f, 30f / 255f, 1f)
  override def offRoadHighColor(): Color = new Color(50f / 255f, 170f / 255f, 70f / 255f, 1f)

  override def roadLowColor(): Color = new Color(70f / 255f, 70f / 255f, 70f / 255f, 1f)
  override def roadHighColor(): Color = new Color(110f / 255f, 110f / 255f, 110f / 255f, 1f)

  override def shoulderLowColor(): Color = new Color(40f / 255f, 150f / 255f, 55f / 255f, 1f)
  override def shoulderHighColor(): Color = new Color(50f / 255f, 40f / 255f, 10f / 255f, 1f)

  override def skyImage(): BitmapImage = sky

  override def parallaxLayers(): Array[ParallaxLayer] = layers

  override def generateMapItems(geometry: TrackGeometry): List[AItem] = {
    val random = new Random()
    val trackWidth = geometry.trackSize.getWidth + MapTexture.MAP_PADDING * 2
    val trackHeight = geometry.trackSize.getHeight + MapTexture.MAP_PADDING * 2

    val originX = geometry.trackSize.x - MapTexture.MAP_PADDING
    val originY = geometry.trackSize.y - MapTexture.MAP_PADDING

    val res = List.fill(2500) {
      val item = new SimpleTree()

      do {
        item.Coordinates.x = random.nextFloat() * trackWidth + originX
        item.Coordinates.y = random.nextFloat() * trackHeight + originY
      }while(item.isGenerationAllowed(geometry))
      item
    }

    val res2 = List.fill(200) {
      val item = new SimpleRock()
      do {
        item.Coordinates.x = random.nextFloat() * trackWidth + originX
        item.Coordinates.y = random.nextFloat() * trackHeight + originY
      } while (item.isGenerationAllowed(geometry))
      item
    }

    val res3 = List.fill(300) {
      val item = new SimpleBush()
      do {
        item.Coordinates.x = random.nextFloat() * trackWidth + originX
        item.Coordinates.y = random.nextFloat() * trackHeight + originY
      } while (item.isGenerationAllowed(geometry))
      item
    }

    res.appendedAll(res2).appendedAll(res3)
  }
}