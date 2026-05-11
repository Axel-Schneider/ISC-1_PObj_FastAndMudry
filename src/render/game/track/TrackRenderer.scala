package ch.hevs.fastandmudry
package render.game.track

import core.world.World
import render.AbstractRenderer
import render.Data.Game
import utils.Constant

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class TrackRenderer extends AbstractRenderer {
  val Track = World.INSTANCE.TRACK
  val CONST = Constant.Game.View
  val playerCurvatureDiff = World.INSTANCE.CAR.Curvature-Track.Curvature
  override def onGraphicRender(g: GdxGraphics): Unit = {
    val halfScreenHeigh = g.getScreenHeight / 2f
    var dx = 0f
    var x = 0f
    for(y <- 0 to halfScreenHeigh.toInt) {
      val mathematiqueY = y - Constant.Game.View.Car.MARGIN_BOTTOM
      val perspective = 1f - y / halfScreenHeigh
      val invertedPerspective = 1f-perspective
      val c = Track.getTrackAt(Game.Distance + invertedPerspective*CONST.Distance - Constant.Game.View.Car.MARGIN_BOTTOM)._1
      val dc = c / halfScreenHeigh
      dx += dc
      x += dx / halfScreenHeigh

      var roadWidth = CONST.Track.MIN_ROAD_WIDTH_PERCENTAGE + perspective * CONST.Track.ROAD_WIDTH_PERCENTAGE
      val playerX = ((World.INSTANCE.CAR.RoadPosition)/2)*roadWidth*1.5f
      println(f"${World.INSTANCE.CAR.RoadPosition} - ${(World.INSTANCE.CAR.RoadPosition)/2}")
      val middlePoint = CONST.Track.BASE_MIDDLE_POINT + (x-playerX) + (Track.CurrentCurvature * math.pow(perspective, 1.5).toFloat)*(playerX*perspective)
      val clipWidth = roadWidth * CONST.Track.CLIP_WIDTH_PERCENTAGE

      roadWidth *= 0.5f   // split for half of the screen width

      val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
      val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
      val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
      val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

      val waveColor = math.sin(CONST.Track.WAVE_FREQUENCY * math.pow(invertedPerspective, 3) + Game.Distance * 0.5f).toFloat
      val grassColor = new Color(0.1f, 0.5f + (Math.abs(waveColor) * 0.3f), 0.1f, 1.0f)

      g.drawLine(0, y, leftGrass, y, grassColor)
      g.drawLine(leftGrass, y, leftClip, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(leftClip, y, rightClip, y, Color.DARK_GRAY)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }
  }
}
