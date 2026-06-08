package ch.hevs.fastandmudry
package screens.death

import core.audio.{AudioManager, MusicTrack}
import core.state.{BackToMenu, GameStateMachine}
import screens.AbstractScreen

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.utils.Constant.{CINEMATIC, DEATH}
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}

class DeathScreen extends AbstractScreen {
  private val background: BitmapImage = new BitmapImage(DEATH.BACKGROUND_IMAGE)
  private val carImage: BitmapImage = new BitmapImage(World.INSTANCE.selectedSkin.openHoodImagePath)

  override def onInit(): Unit = {
    AudioManager.playTrack(MusicTrack.Menu)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    drawBackground(g, background)
    drawCar(g)

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      GameStateMachine.handle(BackToMenu)
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

  private def drawCar(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = carImage.getImage.getWidth.toFloat
    val imgH = carImage.getImage.getHeight.toFloat

    val drawW = imgW * 0.35f
    val drawH = imgH * 0.35f

    g.drawTransformedPicture(screenW/1.7f, screenH/10f, 0f, drawW / 2f, drawH / 2f, carImage)
  }

}
