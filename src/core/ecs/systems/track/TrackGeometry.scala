package ch.hevs.fastandmudry
package core.ecs.systems.track

import ch.hevs.gdx2d.lib.utils.catmull.CatmullRomUtils
import com.badlogic.gdx.math.{Rectangle, Vector2}

import scala.collection.mutable.ArrayBuffer

class TrackGeometry(start: Vector2, stop: Vector2, nControlPoints: Int, private val halfRoadWidth: Float) {
  private val centerLine: ArrayBuffer[Vector2] = generateCatmullChain(nControlPoints)
  private val leftLine: ArrayBuffer[Vector2] = createChainWithOffset(halfRoadWidth, centerLine)
  private val rightLine: ArrayBuffer[Vector2] = createChainWithOffset(-halfRoadWidth, centerLine)

  lazy val trackSize: Rectangle = calculateTrackSize()

  def CenterLine: ArrayBuffer[Vector2] = centerLine
  def LeftLine: ArrayBuffer[Vector2] = leftLine
  def RightLine: ArrayBuffer[Vector2] = rightLine
  def FinishPoint: Vector2 = centerLine(centerLine.length - 1)
  def HalfRoadWidth: Float = halfRoadWidth

  def isRoad(p: Vector2): Boolean = {
    var minDist = 999999f
    for(v <- centerLine) {
      val d = p.dst2(v) // dist(p, v) = (vx - px)^2 + (vy - py) ^2
      if(d < minDist)minDist = d
    }
    return minDist <= halfRoadWidth  * halfRoadWidth // d^2 <= w^2
  }


  /**
   * Create a chain from {@link # start} to {@link # stop} using {@code nPoints}.
   *
   * @param nPoints The number of points from the start to the stop position
   */
  private def generateCatmullChain(nPoints: Int): ArrayBuffer[Vector2] = {
    var vertices = randomVertices(nPoints, 200f)
    // Interpolates new points with a Catmull-Rom spline, using 9 subdivisions per segment
    val spline: Array[Vector2] = CatmullRomUtils.subdividePoints(vertices.toArray, 60)
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
      val yNoise = ((Math.random - 0.5) * 2.0 * randomHeight).toFloat
      val h: Float = start.y + height * i + yNoise
      val xNoise = if (i == 0 || i == nPoints - 1) 0f else ((Math.random - 0.5) * 2.0 * width * 0.4).toFloat
      val p: Vector2 = new Vector2(start.x + width * i + xNoise, h)
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
  def isFinishLine(p: Vector2): Boolean =
    p.dst(FinishPoint) <= halfRoadWidth

  private def calculateTrackSize(): Rectangle = {
    var minX = 999999999999f
    var maxX = 0f
    var minY = 999999999999f
    var maxY = 0f

    def checkMinMax(chain: ArrayBuffer[Vector2]): Unit = {
      for (i <- chain.indices) {
        val v = chain(i)
        if (v.x < minX) minX = v.x
        if (v.x > maxX) maxX = v.x
        if (v.y < minY) minY = v.y
        if (v.y > maxY) maxY = v.y
      }
    }

    checkMinMax(centerLine)
    checkMinMax(leftLine)
    checkMinMax(rightLine)

    return new Rectangle(minX, minY, maxX - minX, maxY - minY)
  }
}

