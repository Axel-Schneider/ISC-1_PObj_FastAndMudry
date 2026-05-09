package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class GameScreen extends AbstractScreen {

  var carRoadPosition = 0f;

  val CAR_WIDTH = 33;
  val CAR_HEIGHT = 100;
  val CAR_MARGIN_BOTTOM = 100;

  override def onInit(): Unit = { }

  override def onGraphicRender(g: GdxGraphics): Unit = {g.clear()
    g.clear()
    g.setColor(Color.WHITE)
    g.drawStringCentered(g.getScreenHeight - 50, "GAME VIEW !")

    for(y <- 0 to (g.getScreenHeight / 2)) {
      for(x <- 0 to g.getScreenWidth) {
        val perspective = y / (g.getScreenHeight / 2f)
        val middlePoint = 0.5f
        var roadWidth = 0.9f - perspective * 0.8f
        val clipWidth = roadWidth * 0.15f

        roadWidth *= 0.5f

        val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
        val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
        val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
        val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

        if (x >= 0 && x < leftGrass || x > rightGrass) {
          g.setPixel(x, y, Color.FOREST)
        }
        else if (x < leftClip || x > rightClip) {
          g.setPixel(x, y, if(y % 100 < 50) Color.RED else Color.WHITE)
        }
        else if (x < rightClip) {
          // let black
        }
      }
    }

    // draw car
    val carPosScreen = g.getScreenWidth / 2 + (g.getScreenWidth * carRoadPosition / 2)
    g.setColor(Color.BLUE)
    g.drawFilledRectangle(carPosScreen, CAR_MARGIN_BOTTOM + CAR_HEIGHT, CAR_WIDTH, CAR_HEIGHT, 0)

  }
}
