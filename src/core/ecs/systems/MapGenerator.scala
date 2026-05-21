package ch.hevs.fastandmudry
package core.ecs.systems

import ch.hevs.gdx2d.lib.utils.catmull.CatmullRomUtils
import com.badlogic.gdx.math.Vector2

import java.util
import scala.collection.mutable.ArrayBuffer
/*
  The CatmullChain code has been taken from the ch.hevs.gdx2d.components.physics.PhysicsChain lib
  and been modified to adapt it to our situation.
 */
class MapGenerator(protected var start: Vector2, protected var stop: Vector2, nControlPoints: Int) {
  val mainChain: ArrayBuffer[Vector2] = generateCatmullChain(nControlPoints)
  val leftChain: ArrayBuffer[Vector2] = createChainWithOffset(10f, mainChain)
  val rightChain: ArrayBuffer[Vector2] = createChainWithOffset(-10f, mainChain)


  println("####")
  println(s"x: ${mainChain(0).x} // y: ${mainChain(0).y}")
  println(s"x: ${mainChain(1).x} // y: ${mainChain(1).y}")

  /**
   * Create a chain from {@link # start} to {@link # stop} using {@code nPoints}.
   *
   * @param nPoints The number of points from the start to the stop position
   */
  private def generateCatmullChain(nPoints: Int): ArrayBuffer[Vector2] = {
    var vertices = randomVertices(nPoints, 0.8f)
    // Interpolates new points with a Catmull-Rom spline, using 9 subdivisions per segment
    val spline: Array[Vector2] = CatmullRomUtils.subdividePoints(vertices.toArray, 9)
    // Replace the existing vertices by the spline generated
    vertices.clear()
    for (i <- spline.indices) {
      vertices.addOne(spline(i))
    }
    return vertices
  }
  private def randomVertices(nPoints: Int, randomHeight: Float): ArrayBuffer[Vector2] = {
    val vertices: ArrayBuffer[Vector2] = new ArrayBuffer[Vector2]
    val width: Float = (stop.x - start.x) / (nPoints - 1)
    val height: Float = (stop.y - start.y) / (nPoints - 1)
    for (i <- 0 until nPoints) {
      var h: Float = start.y + height * i
      h += (randomHeight * (Math.random * h)).toFloat
      val p: Vector2 = new Vector2(start.x + width * i, h)
      vertices.addOne(p)
    }
    vertices
  }
  private def createChainWithOffset(offset: Float, chain: ArrayBuffer[Vector2]): ArrayBuffer[Vector2] = {
    val newChain: ArrayBuffer[Vector2] = new ArrayBuffer[Vector2]
    for (i <- chain.indices) {
      val v = chain(i)
      newChain.addOne(new Vector2(v.x, v.y + offset))
    }
    return newChain
  }

  // AI-generated just in test phase for previsualisation
  def printAsciiPreview(cols: Int = 120, rows: Int = 30): Unit = {
    if (mainChain.isEmpty) { println("(empty chain)"); return }
    var minX = Float.PositiveInfinity; var maxX = Float.NegativeInfinity
    var minY = Float.PositiveInfinity; var maxY = Float.NegativeInfinity
    for (i <- mainChain.indices) {
      val v = mainChain(i)
      if (v.x < minX) minX = v.x
      if (v.x > maxX) maxX = v.x
      if (v.y < minY) minY = v.y
      if (v.y > maxY) maxY = v.y
    }

    // Aspect-ratio preserving scale:
    // X gets the full column width; Y is mapped so 1 row covers the SAME world
    // distance as 1 column (times a terminal-aspect fudge factor since character
    // cells are taller than wide). This stops a small jitter from filling the
    // whole vertical space and looking like giant pikes.
    val charAspect  = 2.0f
    val rangeX      = math.max(maxX - minX, 1e-3f)
    val unitsPerCol = rangeX / (cols - 1)
    val unitsPerRow = unitsPerCol * charAspect

    // Pick just enough rows to hold the actual Y span (+ a 1-row pad each side).
    val rangeY      = math.max(maxY - minY, 1e-3f)
    val rowsNeeded  = math.ceil(rangeY / unitsPerRow).toInt + 2
    val actualRows  = math.max(1, math.min(rows, rowsNeeded))

    // Center the view on the road's Y midline.
    val centerY  = (minY + maxY) / 2f
    val halfH    = (actualRows / 2f) * unitsPerRow
    val viewMinY = centerY - halfH
    val viewMaxY = centerY + halfH

    val grid = Array.fill(actualRows, cols)(' ')
    for (i <- mainChain.indices) {
      val v  = mainChain(i)
      val gx = (((v.x - minX) / rangeX) * (cols - 1)).toInt
      // Flip Y so larger Y appears at the top.
      val gy = (((viewMaxY - v.y) / (viewMaxY - viewMinY)) * (actualRows - 1)).toInt
      if (gx >= 0 && gx < cols && gy >= 0 && gy < actualRows) grid(gy)(gx) = '#'
    }
    for (i <- leftChain.indices) {
      val v  = leftChain(i)
      val gx = (((v.x - minX) / rangeX) * (cols - 1)).toInt
      // Flip Y so larger Y appears at the top.
      val gy = (((viewMaxY - v.y) / (viewMaxY - viewMinY)) * (actualRows - 1)).toInt
      if (gx >= 0 && gx < cols && gy >= 0 && gy < actualRows) grid(gy)(gx) = '*'
    }
    for (i <- rightChain.indices) {
      val v  = rightChain(i)
      val gx = (((v.x - minX) / rangeX) * (cols - 1)).toInt
      // Flip Y so larger Y appears at the top.
      val gy = (((viewMaxY - v.y) / (viewMaxY - viewMinY)) * (actualRows - 1)).toInt
      if (gx >= 0 && gx < cols && gy >= 0 && gy < actualRows) grid(gy)(gx) = '"'
    }
    val border = "+" + ("-" * cols) + "+"
    println(f"MapGenerator preview  (${mainChain.size} samples,  x=[$minX%.1f..$maxX%.1f]  y=[$minY%.1f..$maxY%.1f],  ${unitsPerCol}%.1f px/col)")
    println(border)
    grid.foreach(row => println("|" + row.mkString + "|"))
    println(border)
  }
}

object MapGeneratorPreview {
  def main(args: Array[String]): Unit = {
    val gen = new MapGenerator(new Vector2(0f, 100f), new Vector2(1000f, 100f), 8)
    gen.printAsciiPreview(800, 1600)
  }
}
