package ch.hevs.fastandmudry
package utils.noise

// The code comes from this repo: https://github.com/yatsukha/perlin-noise
object Noise {
  def noise(g: IndexedSeq[IndexedSeq[(Double, Double)]], p: (Double, Double)): Double = {
    // floors and ceils of the point, aka bounds
    val bx  = (p._1.toInt, (p._1 + 1).toInt)
    val by  = (p._2.toInt, (p._2 + 1).toInt)

    // distances to the bounds
    val dx  = (p._1 - bx._1, p._1 - bx._2)
    val dy  = (p._2 - by._1, p._2 - by._2)

    // smooth step of the upperleft bound is used as weight
    val w   = (Util.smoothStep(dx._1), Util.smoothStep(dy._1))

    // dot product for upper bounds
    var vx0 = dot(g(bx._1)(by._1), (dx._1, dy._1))
    var vx1 = dot(g(bx._2)(by._1), (dx._2, dy._1))

    val vy0 = scale(vx0, vx1, w._1)

    // dot product for lower bounds
    vx0 = dot(g(bx._1)(by._2), (dx._1, dy._2))
    vx1 = dot(g(bx._2)(by._2), (dx._2, dy._2))

    val vy1 = scale(vx0, vx1, w._1)

    scale(vy0, vy1, w._2)
  }

  private def dot(a: (Double, Double), b: (Double, Double)): Double =
    a._1 * b._1 + a._2 * b._2

  private def scale(x: Double, y: Double, w: Double): Double =
    (1.0 - w) * x + w * y


  def smooth(grid: Array[Double], rows: Int, cols: Int, x: Double, y: Double): Double = {
    val ix = x.toInt
    val iy = y.toInt
    val fx = x - ix
    val fy = y - iy

    val x0 = ix % rows
    val x1 = (ix + 1) % rows
    val y0 = iy % cols
    val y1 = (iy + 1) % cols

    val c00 = grid(x0 * cols + y0)
    val c01 = grid(x0 * cols + y1)
    val c10 = grid(x1 * cols + y0)
    val c11 = grid(x1 * cols + y1)

    c00 * (1 - fx) * (1 - fy) +
      c01 * (1 - fx) * fy +
      c10 * fx * (1 - fy) +
      c11 * fx * fy
  }

  def turbulence(grid: Array[Double], rows: Int, cols: Int, s: Int, x: Int, y: Int): Double = {
    var counter = s
    var value = 0.0
    while (counter >= 1) {
      value += smooth(grid, rows, cols, x.toDouble / counter, y.toDouble / counter) * counter
      counter = counter / 2
    }
    value / (2 * s.toDouble)
  }
}
