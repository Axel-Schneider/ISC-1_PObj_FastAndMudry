package ch.hevs.fastandmudry
package render

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class TrackRenderer extends AbstractPerspectiveRenderer {

  override def onGraphicRender(g: GdxGraphics, distance: Float, curvature: Float): Unit = {
    for(y <- 0 to (g.getScreenHeight / 2)) {
      val perspective = 1f - y / (g.getScreenHeight / 2f)
      val middlePoint = 0.5f + World.INSTANCE.TRACK.CurrentCurvature * math.pow(1f - perspective, 2).toFloat
      var roadWidth = 0.01f + perspective * 0.8f
      val clipWidth = roadWidth * 0.15f

      roadWidth *= 0.5f

      val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
      val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
      val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
      val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

      val waveColor = math.sin(30f * math.pow(1f - perspective, 3) + distance * 0.5f).toFloat
      val grassColor = new Color(0.1f, 0.5f + (Math.abs(waveColor) * 0.3f), 0.1f, 1.0f)

      g.drawLine(0, y, leftGrass, y, grassColor)
      g.drawLine(leftGrass, y, leftClip, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(leftClip, y, rightClip, y, Color.DARK_GRAY)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }
  }
}
