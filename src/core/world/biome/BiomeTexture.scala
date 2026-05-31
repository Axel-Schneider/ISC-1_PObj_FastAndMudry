package ch.hevs.fastandmudry
package core.world.biome

import utils.noise.{Generator, Noise}

import com.badlogic.gdx.graphics.Color

object BiomeTexture {

  // Most of the code come from this git repo: https://github.com/yatsukha/perlin-noise
  def generateNoiseField(width: Int, height: Int, noiseCell: Int, low: Color, high: Color): Array[Array[Int]] = {
    val r: Int = width
    val c: Int = height
    val t = noiseCell
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

    val perlinFlat = flatten(perlin, r, c)

    val field = Array.ofDim[Int](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val noiseVal = Noise.turbulence(perlinFlat, r, c, 8, x, y)

        val red = low.r + (high.r - low.r) * noiseVal
        val g = low.g + (high.g - low.g) * noiseVal
        val b = low.b + (high.b - low.b) * noiseVal

        val finalR = math.min(1.0, math.max(0.0, red)).toFloat
        val finalG = math.min(1.0, math.max(0.0, g)).toFloat
        val finalB = math.min(1.0, math.max(0.0, b)).toFloat

        field(x)(y) = Color.rgba8888(finalR, finalG, finalB, 1f)
      }
    }
    field
  }

  def generateScalarField(width: Int, height: Int, noiseCell: Int): Array[Array[Float]] = {
    val r = width
    val c = height
    val t = noiseCell
    val gf = Generator.gradientField(
      new scala.util.Random(System.currentTimeMillis),
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
              .map(_ + 1)
              .map(_ / 2)
        )

    val perlinFlat = flatten(perlin, r, c)

    val field = Array.ofDim[Float](width, height)
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val n = Noise.turbulence(perlinFlat, r, c, 8, x, y)
        field(x)(y) = math.min(1.0, math.max(0.0, n)).toFloat
      }
    }
    field
  }

  // flatten the IndexedSeq[IndexedSeq[Double]] to a 1D array -> improve performances
  private def flatten(perlin: IndexedSeq[IndexedSeq[Double]], rows: Int, cols: Int): Array[Double] = {
    val flat = new Array[Double](rows * cols)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        flat(i * cols + j) = perlin(i)(j)
      }
    }
    return flat
  }
}
