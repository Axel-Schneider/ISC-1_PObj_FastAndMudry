package ch.hevs.fastandmudry
package render.game

import render.AbstractRenderer
import render.Data.Game

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class TrackRenderer extends AbstractRenderer {
  val VIEW_DISTANCE = 200f
  val Track = World.INSTANCE.TRACK
  override def onGraphicRender(g: GdxGraphics): Unit = {
    var dx = 0f
    var x = 0f
    for(y <- 0 to (g.getScreenHeight / 2)) {
      val perspective = 1f - y / (g.getScreenHeight / 2f)
      val c = Track.getTrackAt(Game.Distance + (1-perspective)*VIEW_DISTANCE)._1
      dx += c / (g.getScreenHeight / 2)
      x += dx / (g.getScreenHeight / 2)

      val middlePoint = 0.5f + x
      var roadWidth = 0.01f + perspective * 0.8f
      val clipWidth = roadWidth * 0.15f

      roadWidth *= 0.5f

      val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
      val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
      val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
      val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

      val waveColor = math.sin(30f * math.pow(1f - perspective, 3) + Game.Distance * 0.5f).toFloat
      val grassColor = new Color(0.1f, 0.5f + (Math.abs(waveColor) * 0.3f), 0.1f, 1.0f)

      g.drawLine(0, y, leftGrass, y, grassColor)
      g.drawLine(leftGrass, y, leftClip, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(leftClip, y, rightClip, y, Color.DARK_GRAY)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }
  }
}
