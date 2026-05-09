package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color

class GameScreen extends AbstractScreen {

  var carRoadPosition = 0f;
  var carDistance = 0f

  val CAR_WIDTH = 33;
  val CAR_HEIGHT = 100;
  val CAR_MARGIN_BOTTOM = 100;

  override def onInit(): Unit = { }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.setColor(Color.WHITE)
    g.drawStringCentered(g.getScreenHeight - 50, "GAME VIEW !")

    carDistance += 100f * Gdx.graphics.getDeltaTime

    val middlePoint = 0.5f

    for(y <- 0 to (g.getScreenHeight / 2)) {
      val perspective = 1f - y / (g.getScreenHeight / 2f)
      var roadWidth = 0.1f + perspective * 0.8f
      val clipWidth = roadWidth * 0.15f

      roadWidth *= 0.5f

      val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
      val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
      val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
      val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

      val waveColor = math.sin(20f * math.pow(1f - perspective, 3) + carDistance * 0.1f).toFloat
      val grassColor = new Color(0.1f, 0.5f + (waveColor * 0.2f), 0.1f, 1.0f)

      g.drawLine(0, y, leftGrass, y, grassColor)
      g.drawLine(leftGrass, y, leftClip, y, if(waveColor < 0.2) Color.WHITE else Color.RED)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0.2) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }

    // draw car
    val carPosScreen = g.getScreenWidth / 2 + (g.getScreenWidth * carRoadPosition / 2)
    g.setColor(Color.BLUE)
    g.drawFilledRectangle(carPosScreen, CAR_MARGIN_BOTTOM + CAR_HEIGHT, CAR_WIDTH, CAR_HEIGHT, 0)

  }
}
