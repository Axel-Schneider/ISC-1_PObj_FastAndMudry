package ch.hevs.fastandmudry
package screens.cinematic

import core.state.{EndDayCinematicEnded, FinalCinematicEnded, GameState, GameStateMachine, StartDayCinematicEnded}
import screens.AbstractScreen

import utils.Constant.CINEMATIC
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class CinematicScreen extends AbstractScreen {
  private var timeElapsed: Float = 0f
  private val DURATION: Float = 5f
  private val background: BitmapImage = new BitmapImage(CINEMATIC.BACKGROUND_IMAGE)
  private val carImage: BitmapImage = new BitmapImage(CINEMATIC.CAR_IMAGE)

  override def onInit(): Unit = {
    timeElapsed = 0f
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    timeElapsed += Gdx.graphics.getDeltaTime
    drawBackground(g)
    drawCar(g, 0f, 0f, g.getScreenWidth.toFloat / 2, carImage.getImage.getHeight.toFloat)

    if (timeElapsed >= DURATION) {
      GameStateMachine.getGameState match {
        case GameState.StartDayCinematic(_) => GameStateMachine.handle(StartDayCinematicEnded)
        case GameState.EndDayCinematic(_) => GameStateMachine.handle(EndDayCinematicEnded)
        case GameState.FinalCinematic => GameStateMachine.handle(FinalCinematicEnded)
        case _ => // nothing
      }
    }
  }

  private def drawBackground(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = background.getImage.getWidth.toFloat
    val imgH = background.getImage.getHeight.toFloat

    val scale = math.min(screenW / imgW, screenH / imgH)
    val drawW = imgW * scale
    val drawH = imgH * scale

    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, drawW / 2f, drawH / 2f, background)
  }

  private def drawCar(g: GdxGraphics, startX: Float, startY: Float, endX: Float, endY: Float): Unit = {
    val imgW = carImage.getImage.getWidth.toFloat
    val imgH = carImage.getImage.getHeight.toFloat

    val dirX = if(startX > endX) -1 else 1
    val dirY = if(startY > endY) -1 else 1

    var x = dirX * timeElapsed*400f
    var y = dirY * timeElapsed*200f

    x = if(x <= endX) x else endX
    y = if(y <= endY) y else endY

    g.drawTransformedPicture(startX + x, startY + y, 0f, imgW / 2f, imgH / 2f, carImage)
  }
}
