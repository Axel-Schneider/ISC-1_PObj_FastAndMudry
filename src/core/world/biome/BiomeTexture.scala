package ch.hevs.fastandmudry
package core.world.biome

import utils.noise.{Generator, Noise}

import com.badlogic.gdx.graphics.Color

object BiomeTexture {

  // Most of the code come from this git repo: https://github.com/yatsukha/perlin-noise
  def generateNoiseField(width: Int, height: Int, noiseCell: Int,
                         low: Color, high: Color): Array[Array[Color]] = {
    val r = width
    val c = height
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

    val field = Array.ofDim[Color](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val noiseVal = Noise.turbulence(perlin, 8)(x, y)

        val r = low.r + (high.r - low.r) * noiseVal
        val g = low.g + (high.g - low.g) * noiseVal
        val b = low.b + (high.b - low.b) * noiseVal

        val finalR = math.min(1.0, math.max(0.0, r)).toFloat
        val finalG = math.min(1.0, math.max(0.0, g)).toFloat
        val finalB = math.min(1.0, math.max(0.0, b)).toFloat

        field(x)(y) = new Color(finalR, finalG, finalB, 1f)
      }
    }
    field
  }
}
