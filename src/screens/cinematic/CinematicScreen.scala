package ch.hevs.fastandmudry
package screens.cinematic

import core.audio.{AudioManager, MusicTrack}
import core.state.{EndDayCinematicEnded, FinalCinematicEnded, GameState, GameStateMachine, StartDayCinematicEnded}
import screens.AbstractScreen
import utils.Constant.CINEMATIC

import core.world.World
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx

class CinematicScreen extends AbstractScreen {
  private var timeElapsed: Float = 0f
  private val DURATION: Float = 5f
  private val FINAL_DURATION: Float = 10f
  private val carImage: BitmapImage = new BitmapImage(World.INSTANCE.selectedSkin.sideImagePath)
  private val frontFinalBackground: BitmapImage = new BitmapImage(CINEMATIC.FRONT_FINAL_BACKGROUND_IMAGE)
  private val backFinalBackground: BitmapImage = new BitmapImage(CINEMATIC.BACK_FINAL_BACKGROUND_IMAGE)
  private val mudryImage: BitmapImage = new BitmapImage(CINEMATIC.MUDRY_BACKGROUND_IMAGE)

  override def onInit(): Unit = {
    AudioManager.playTrack(MusicTrack.Cinematic)
    timeElapsed = 0f
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    timeElapsed += Gdx.graphics.getDeltaTime

    GameStateMachine.getGameState match {
      case GameState.StartDayCinematic(day) =>
        drawBackground(g, day.startDayCinematicBackground)
        drawCar(g, g.getScreenWidth.toFloat / 2, carImage.getImage.getHeight.toFloat, 0f, 0f)
        if (timeElapsed >= DURATION) GameStateMachine.handle(StartDayCinematicEnded)

      case GameState.EndDayCinematic(day) =>
        drawBackground(g, day.endDayCinematicBackground)
        drawCar(g, 0f, 0f, g.getScreenWidth.toFloat / 2, carImage.getImage.getHeight.toFloat)
        if (timeElapsed >= DURATION) GameStateMachine.handle(EndDayCinematicEnded)

      case GameState.FinalCinematic =>
        drawFinalBackground(g, frontFinalBackground, backFinalBackground, mudryImage, g.getScreenWidth.toFloat / 2, 1f, g.getScreenHeight / 1.5f)
        drawCar(g, 0f, 0f, g.getScreenWidth.toFloat / 1.5f, g.getScreenHeight / 5f)
        if (timeElapsed >= FINAL_DURATION) GameStateMachine.handle(FinalCinematicEnded)

      case _ =>
    }
  }

  private def drawBackground(g: GdxGraphics, background: BitmapImage): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = background.getImage.getWidth.toFloat
    val imgH = background.getImage.getHeight.toFloat

    val scale = math.min(screenW / imgW, screenH / imgH)
    val drawW = imgW * scale
    val drawH = imgH * scale

    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, drawW / 2f, drawH / 2f, background)
  }

  private def drawFinalBackground(g: GdxGraphics, frontBackground: BitmapImage, backBackground: BitmapImage, mudry: BitmapImage, x: Float, y: Float, endY: Float): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = frontBackground.getImage.getWidth.toFloat
    val imgH = frontBackground.getImage.getHeight.toFloat

    val scale = math.min(screenW / imgW, screenH / imgH)
    val drawW = imgW * scale
    val drawH = imgH * scale

    val mudryW = mudry.getImage.getWidth.toFloat
    val mudryH = mudry.getImage.getHeight.toFloat
    var yT = y * timeElapsed*100f

    yT = if(yT <= endY) yT else endY

    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, drawW / 2f, drawH / 2f, backBackground)
    g.drawTransformedPicture(x, yT,  0f, mudryW / 2f, mudryH / 2f, mudry)
    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, drawW / 2f, drawH / 2f, frontBackground)
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
