package ch.hevs.fastandmudry
package core.ecs.systems.track

import ch.hevs.fastandmudry.utils.Constant.MapTexture
import ch.hevs.gdx2d.lib.utils.catmull.CatmullRomUtils
import com.badlogic.gdx.math.{Rectangle, Vector2}

import scala.collection.mutable.ArrayBuffer

class TrackGeometry(start: Vector2, stop: Vector2, nControlPoints: Int) {
  private val halfRoadWidth: Float = MapTexture.HALF_ROAD_WIDTH
  private val halfShoulderWidth: Float = MapTexture.HALF_SHOULDER_WIDTH
  private val centerLine: ArrayBuffer[Vector2] = generateCatmullChain(nControlPoints)
  private val maxRadius: Float = halfShoulderWidth + MapTexture.SHOULDER_JITTER

  lazy val trackSize: Rectangle = calculateTrackSize()

  def CenterLine: ArrayBuffer[Vector2] = centerLine
  def FinishPoint: Vector2 = centerLine(centerLine.length - 1)
  def HalfRoadWidth: Float = halfRoadWidth
  def HalfShoulderWidth: Float = halfShoulderWidth

  private case class SegmentBounds(startVector: Vector2, endVector: Vector2, minX: Float, maxX: Float, minY: Float, maxY: Float)
  private lazy val segmentBounds: Array[SegmentBounds] = {
    val bounds = new ArrayBuffer[SegmentBounds]
    for (i <- 0 until centerLine.length - 1) {
      val startVector = centerLine(i)
      val endVector = centerLine(i + 1)

      // make a box tight to the segment
      val innerMinX = math.min(startVector.x, endVector.x)
      val innerMaxX = math.max(startVector.x, endVector.x)
      val innerMinY = math.min(startVector.y, endVector.y)
      val innerMaxY = math.max(startVector.y, endVector.y)

      // add a radius so it also covers the road and shoulder
      val minX = innerMinX - maxRadius
      val maxX = innerMaxX + maxRadius
      val minY = innerMinY - maxRadius
      val maxY = innerMaxY + maxRadius

      val box = SegmentBounds(startVector, endVector, minX, maxX, minY, maxY)
      bounds.addOne(box)
    }

    bounds.toArray
  }

  def distToCenterLineSq(p: Vector2): Float =
    distToCenterLineSq(p.x, p.y)

  def distToCenterLineSq(px: Float, py: Float): Float = {
    var minDist = 999999f
    var i = 0
    // while loop is more performant in this case (runned millions/billions of times) than a for loop
    while (i < segmentBounds.length) {
      val b = segmentBounds(i)
      // only do math if it is in the segment bound (increase performance)
      if (px >= b.minX && px <= b.maxX && py >= b.minY && py <= b.maxY) {
        val d = distToSegmentSquared(px, py, b.startVector, b.endVector)
        if (d < minDist) minDist = d
      }
      i += 1
    }
    return minDist
  }

  // Taken from: https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
  private def sqr(x: Float): Float = x * x
  private def dist2(v: Vector2, w: Vector2): Float = sqr(v.x - w.x) + sqr(v.y - w.y)

  private def distToSegmentSquared(px: Float, py: Float, v: Vector2, w: Vector2): Float = {
    val l2 = dist2(v, w)
    if (l2 == 0f) return sqr(px - v.x) + sqr(py - v.y)
    var t = ((px - v.x) * (w.x - v.x) + (py - v.y) * (w.y - v.y)) / l2
    t = math.max(0f, math.min(1f, t))
    val x = v.x + t * (w.x - v.x)
    val y = v.y + t * (w.y - v.y)
    return sqr(px - x) + sqr(py - y)
  }

  def isOffRoad(p: Vector2): Boolean =
    distToCenterLineSq(p) > halfShoulderWidth * halfShoulderWidth

  /**
   * Create a chain from {@link # start} to {@link # stop} using {@code nPoints}.
   *
   * @param nPoints The number of points from the start to the stop position
   */
  private def generateCatmullChain(nPoints: Int): ArrayBuffer[Vector2] = {
    val vertices = randomVertices(nPoints, 200f)
    // Interpolates new points with a Catmull-Rom spline, using 30 subdivisions per segment
    val spline: Array[Vector2] = CatmullRomUtils.subdividePoints(vertices.toArray, 30)
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

  def isFinishLine(p: Vector2): Boolean =
    isFinishLine(p.x, p.y)

  def isFinishLine(px: Float, py: Float): Boolean =
    sqr(px - FinishPoint.x) + sqr(py - FinishPoint.y) <= halfRoadWidth * halfRoadWidth

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

    return new Rectangle(minX, minY, maxX - minX, maxY - minY)
  }
}
