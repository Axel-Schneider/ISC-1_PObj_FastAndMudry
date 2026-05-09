package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import jdk.internal.util.xml.impl.Pair

import scala.collection.mutable.ArrayBuffer

class GameScreen extends AbstractScreen {

  var carRoadPosition = 0f;
  var carDistance = 0f
  var carSpeed = 0.5f

  var roadCurvature = 0f

  val CAR_WIDTH = 33;
  val CAR_HEIGHT = 100;
  val CAR_MARGIN_BOTTOM = 100;


  var trackVector: ArrayBuffer[(Float, Float)] = ArrayBuffer[(Float, Float)]()   // curveture, distance

  override def onInit(): Unit = {
    trackVector.append((0f, 10f))
    trackVector.append((0f, 200f))
    trackVector.append((0.5f, 200f))
    trackVector.append((0f, 400f))
    trackVector.append((-0.5f, 200f))
    trackVector.append((0f, 200f))
    trackVector.append((-0.5f, 200f))
    trackVector.append((0.5f, 200f))
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.setColor(Color.WHITE)
    g.drawStringCentered(g.getScreenHeight - 50, "GAME VIEW !")

    carDistance += (70f*carSpeed) * Gdx.graphics.getDeltaTime

    var offset = 0f
    var trackSection = 0

    while (trackSection < trackVector.size && offset <= carDistance) {
      offset += trackVector(trackSection)._2
      trackSection += 1
    }

    val targetCurvature = trackVector(trackSection-1)._1
    val trackCurvatureDiff = (targetCurvature - roadCurvature) * Gdx.graphics.getDeltaTime * carSpeed
    roadCurvature += trackCurvatureDiff


    for(y <- 0 to (g.getScreenHeight / 2)) {
      val perspective = 1f - y / (g.getScreenHeight / 2f)
      var middlePoint = 0.5f + roadCurvature * math.pow(1f - perspective, 2).toFloat
      var roadWidth = 0.1f + perspective * 0.8f
      val clipWidth = roadWidth * 0.15f

      roadWidth *= 0.5f

      val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
      val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
      val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
      val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

      val waveColor = math.sin(50f * math.pow(1f - perspective, 3) + carDistance * 0.1f).toFloat
      val grassColor = new Color(0.1f, 0.5f + (Math.abs(waveColor) * 0.3f), 0.1f, 1.0f)

      g.drawLine(0, y, leftGrass, y, grassColor)
      g.drawLine(leftGrass, y, leftClip, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }

    // draw car
    val carPosScreen = g.getScreenWidth / 2 + (g.getScreenWidth * carRoadPosition / 2)
    g.setColor(Color.BLUE)
    g.drawFilledRectangle(carPosScreen, CAR_MARGIN_BOTTOM + CAR_HEIGHT, CAR_WIDTH, CAR_HEIGHT, 0)

  }
}
