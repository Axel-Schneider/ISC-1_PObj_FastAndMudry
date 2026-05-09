package ch.hevs.fastandmudry
package screens

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ArrayBuffer

class GameScreen extends AbstractScreen {

  var carRoadPosition = 0f;
  var carDistance = 0f
  var carSpeed = 0f


  var curvature = 0f
  var trackCurvature = 0f
  var trackDistance = 0f
  var carCurvature = 0f
  var carDirection = 0;

  val CAR_WIDTH = 33;
  val CAR_HEIGHT = 100;
  val CAR_MARGIN_BOTTOM = 100;

  var trackVector: ArrayBuffer[(Float, Float)] = ArrayBuffer[(Float, Float)]()   // curveture, distance

  override def onInit(): Unit = {
    trackVector.append((0f, 10f))
    trackVector.append((0f, 200f))
    trackVector.append((1f, 200f))
    trackVector.append((0f, 400f))
    trackVector.append((-1f, 200f))
    trackVector.append((0f, 200f))
    trackVector.append((-1f, 200f))
    trackVector.append((1f, 200f))
    trackVector.append((0f, 200f))

    trackVector.foreach(t => trackDistance += t._2)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.setColor(Color.WHITE)
    g.drawStringCentered(g.getScreenHeight - 50, "GAME VIEW !")

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    // Game logic

    if(Gdx.input.isKeyPressed(Input.Keys.UP))
      carSpeed += 2f * ELAPSED_TIME
    else
      carSpeed -= 1f * ELAPSED_TIME

    carDirection = 0
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      carCurvature -= 0.7f * ELAPSED_TIME
      carDirection += 1
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      carCurvature += 0.7f * ELAPSED_TIME
      carDirection -= 1
    }

    if(math.abs(carCurvature - trackCurvature) >= 0.8f)
      carSpeed -= 5.0f * ELAPSED_TIME

    if(carSpeed > 1) carSpeed = 1
    if(carSpeed < 0) carSpeed = 0

    carDistance += (100f*carSpeed) * ELAPSED_TIME

    var offset = 0f
    var trackSection = 0

    if(carDistance >= trackDistance)
      carDistance -= trackDistance

    // Background

    val screen12 = g.getScreenHeight * 0.5f
    val screen34 = g.getScreenHeight * 0.75f
    for(x <- 0 to g.getScreenWidth) {
      val hillHeight = math.abs(math.sin(x * 0.005f + trackCurvature*2f) * 100f).toFloat
      g.drawLine(x, g.getScreenHeight, x, screen34, Color.NAVY)
      g.drawLine(x, screen34, x, screen12, Color.BLUE)
      g.drawLine(x, screen12+hillHeight, x, screen12, Color.OLIVE)
    }

    // Track

    while (trackSection < trackVector.size && offset <= carDistance) {
      offset += trackVector(trackSection)._2
      trackSection += 1
    }

    val targetCurvature = trackVector(trackSection-1)._1
    val trackCurvatureDiff = (targetCurvature - curvature) * ELAPSED_TIME * carSpeed
    curvature += trackCurvatureDiff

    trackCurvature += curvature * ELAPSED_TIME * carSpeed

    for(y <- 0 to (g.getScreenHeight / 2)) {
      val perspective = 1f - y / (g.getScreenHeight / 2f)
      var middlePoint = 0.5f + curvature * math.pow(1f - perspective, 2).toFloat
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
      g.drawLine(leftClip, y, rightClip, y, Color.DARK_GRAY)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }

    // draw car
    carRoadPosition = carCurvature - trackCurvature
    val carPosScreen = g.getScreenWidth / 2 + (g.getScreenWidth * carRoadPosition / 2)
    g.setColor(Color.BLUE)
    g.drawFilledRectangle(carPosScreen, CAR_MARGIN_BOTTOM + CAR_HEIGHT, CAR_WIDTH, CAR_HEIGHT, carDirection * 30)

  }
}
