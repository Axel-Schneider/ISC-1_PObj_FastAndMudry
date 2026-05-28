package ch.hevs.fastandmudry
package core.world.biome

import core.ecs.systems.Car
import utils.noise.{Generator, Noise}

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.graphics.Color

class DesertBiome extends Biome {
  private val sky: BitmapImage = new BitmapImage("data/parallax/desert/desert_sky.png")
  private val layers: Array[ParallaxLayer] = Array(
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_mountain.png"), -400f, 0.30f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_moon.png"), -800f, 0.20f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_dunemid.png"), -1500f, 0.3f, false),
    new ParallaxLayer(new BitmapImage("data/parallax/desert/desert_dunefront.png"), -2500f, 0.15f, true)
  )

  private val OFFROAD_NOISE_CELL: Int = 24
  private var offRoadNoiseField: Array[Array[Color]] = _

  override def offRoadDecreasingFactorSpeed: Float = 0.2f

  override def getRoadColor(): Color = Color.GRAY

  override def getRoadLineColor(): Color = Color.YELLOW

  override def getOffRoadColor(): Color = new Color(0xffd966ff)

  override def getOffRoadColor(x: Int, y: Int): Color = {
    offRoadNoiseField(x)(y)
  }

  override def prepareOffRoadTexture(width: Int, height: Int): Unit = {
    offRoadNoiseField = generateNoiseField(width, height)
  }

  override def parallaxLayers(): Array[ParallaxLayer] = layers

  override def skyImage(): BitmapImage = sky

  override def updatePhysics(car: Car, isOffRoad: Boolean, elapsedTime: Float): Unit = {
    if(isOffRoad) {
      car.Temperature += 4f * elapsedTime
    } else {

    }
  }

  // Most of the code come from this git repo: https://github.com/yatsukha/perlin-noise
  private def generateNoiseField(width: Int, height: Int): Array[Array[Color]] = {
    val r = width
    val c = height
    val t = OFFROAD_NOISE_CELL
    val gf = Generator.gradientField(
      new scala.util.Random(System.currentTimeMillis),
      // we want the sand to be not too noisy, so we have to divide by NOISE_CELL so it is smoother
      (r / t + 2, c / t + 2)
    )

    val perlin =
      (0 until r)
        .map(_.toDouble / t.toDouble)
        .map(
          x =>
            (0 until c)
              .map(_.toDouble / t.toDouble)
              .map(y => Noise.noise(gf, (x, y)))
              // expand the average [0.3, 0.7] signal energy to full range
              //.map(util.exaggerate(_))
              .map(_ + 1) // move to [0, 2]
              .map(_ / 2) // scale to [0, 1]
        )

    val field = Array.ofDim[Color](width, height)

    val lowR = 180f / 255f
    val lowG = 100f / 255f
    val lowB =  50f / 255f

    val highR = 245f / 255f
    val highG = 220f / 255f
    val highB = 150f / 255f

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val noiseVal = Noise.turbulence(perlin, 8)(x, y)

        val r = lowR + (highR - lowR) * noiseVal
        val g = lowG + (highG - lowG) * noiseVal
        val b = lowB + (highB - lowB) * noiseVal

        val finalR = math.min(1.0, math.max(0.0, r)).toFloat
        val finalG = math.min(1.0, math.max(0.0, g)).toFloat
        val finalB = math.min(1.0, math.max(0.0, b)).toFloat

        field(x)(y) = new Color(finalR, finalG, finalB, 1f)
      }
    }
    field
  }
}
