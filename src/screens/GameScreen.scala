package ch.hevs.fastandmudry
package screens

import ch.hevs.fastandmudry.core.ecs.components.{Car, Track}
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ArrayBuffer

class GameScreen extends AbstractScreen {

  var currentCurvature = 0f

  val CAR: Car = new Car
  val TRACK: Track = new Track

  override def onInit(): Unit = {  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.setColor(Color.WHITE)
    g.drawStringCentered(g.getScreenHeight - 50, "GAME VIEW !")

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    // Game logic

    if(Gdx.input.isKeyPressed(Input.Keys.UP))
      CAR.Speed += 2f * ELAPSED_TIME
    else
      CAR.Speed -= 1f * ELAPSED_TIME

    CAR.Direction = 0
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      CAR.Curvature -= 0.7f * ELAPSED_TIME
      CAR.Direction += 1
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      CAR.Curvature += 0.7f * ELAPSED_TIME
      CAR.Direction -= 1
    }

    if(math.abs(CAR.Curvature - TRACK.Curvature) >= 0.8f)
      CAR.Speed -= 5.0f * ELAPSED_TIME

    CAR.Moving(ELAPSED_TIME, 100)

    // Background

    val screen12 = g.getScreenHeight * 0.5f
    val screen34 = g.getScreenHeight * 0.75f
    for(x <- 0 to g.getScreenWidth) {
      val hillHeight = math.abs(math.sin(x * 0.005f + TRACK.Curvature*2f) * 100f).toFloat
      g.drawLine(x, g.getScreenHeight, x, screen34, Color.NAVY)
      g.drawLine(x, screen34, x, screen12, Color.BLUE)
      g.drawLine(x, screen12+hillHeight, x, screen12, Color.OLIVE)
    }

    // Track
    val targetCurvature = TRACK.getCurrentTrack(CAR.Distance)._1
    val trackCurvatureDiff = (targetCurvature - currentCurvature) * ELAPSED_TIME * CAR.Speed * 0.5f
    currentCurvature += trackCurvatureDiff

    TRACK.Curvature += currentCurvature * ELAPSED_TIME * CAR.Speed

    for(y <- 0 to (g.getScreenHeight / 2)) {
      val perspective = 1f - y / (g.getScreenHeight / 2f)
      var middlePoint = 0.5f + currentCurvature * math.pow(1f - perspective, 2).toFloat
      var roadWidth = 0.1f + perspective * 0.8f
      val clipWidth = roadWidth * 0.15f

      roadWidth *= 0.5f

      val leftGrass = (middlePoint - roadWidth - clipWidth) * g.getScreenWidth
      val leftClip = (middlePoint - roadWidth) * g.getScreenWidth
      val rightGrass = (middlePoint + roadWidth + clipWidth) * g.getScreenWidth
      val rightClip = (middlePoint + roadWidth) * g.getScreenWidth

      val waveColor = math.sin(50f * math.pow(1f - perspective, 3) + CAR.Distance * 0.1f).toFloat
      val grassColor = new Color(0.1f, 0.5f + (Math.abs(waveColor) * 0.3f), 0.1f, 1.0f)

      g.drawLine(0, y, leftGrass, y, grassColor)
      g.drawLine(leftGrass, y, leftClip, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(leftClip, y, rightClip, y, Color.DARK_GRAY)
      g.drawLine(rightClip, y, rightGrass, y, if(waveColor < 0) Color.WHITE else Color.RED)
      g.drawLine(rightGrass, y, g.getScreenWidth, y, grassColor)
    }

    // draw car
    CAR.RoadPosition = CAR.Curvature - TRACK.Curvature
    CAR.draw(g)
  }
}
