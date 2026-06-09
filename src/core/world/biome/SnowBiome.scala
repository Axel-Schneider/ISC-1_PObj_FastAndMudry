package ch.hevs.fastandmudry
package core.world.biome

import ch.hevs.fastandmudry.core.ecs.entities.Item.AItem
import ch.hevs.fastandmudry.core.ecs.entities.Item.forest.SimpleTree
import ch.hevs.fastandmudry.core.ecs.entities.Item.snow.{PinTree, SnowPile, SnowyRock}
import ch.hevs.fastandmudry.core.ecs.systems.track.TrackGeometry
import ch.hevs.fastandmudry.utils.Constant.MapTexture
import core.audio.MusicTrack
import core.ecs.systems.Car
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color

import scala.util.Random

class SnowBiome extends Biome {
  override def musicTrack: MusicTrack = MusicTrack.Snow

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
    car.TireSlippage.IsDefected = isOffRoad
  }

  override def generateMapItems(geometry: TrackGeometry): List[AItem] = {
    val random = new Random()
    val trackWidth = geometry.trackSize.getWidth + MapTexture.MAP_PADDING * 2
    val trackHeight = geometry.trackSize.getHeight + MapTexture.MAP_PADDING * 2

    val originX = geometry.trackSize.x - MapTexture.MAP_PADDING
    val originY = geometry.trackSize.y - MapTexture.MAP_PADDING

    val res = List.fill(2500) {
      val item = new PinTree()

      do {
        item.Coordinates.x = random.nextFloat() * trackWidth + originX
        item.Coordinates.y = random.nextFloat() * trackHeight + originY
      }while(item.isGenerationAllowed(geometry))
      item
    }

    val res2 = List.fill(250) {
      val item = new SnowyRock()

      do {
        item.Coordinates.x = random.nextFloat() * trackWidth + originX
        item.Coordinates.y = random.nextFloat() * trackHeight + originY
      }while(item.isGenerationAllowed(geometry))
      item
    }

    val res3 = List.fill(250) {
      val item = new SnowPile()

      do {
        item.Coordinates.x = random.nextFloat() * trackWidth + originX
        item.Coordinates.y = random.nextFloat() * trackHeight + originY
      }while(item.isGenerationAllowed(geometry))
      item
    }
    res.appendedAll(res2).appendedAll(res3)
  }
}
