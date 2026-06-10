package ch.hevs.fastandmudry
package screens.carSelector

import ch.hevs.fastandmudry.core.audio.{AudioManager, MusicTrack}
import ch.hevs.fastandmudry.core.car.{CarSkin, CarSkins}
import ch.hevs.fastandmudry.core.state.{BackToMenu, CarSkinSelected, GameStateMachine}
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.screens.AbstractScreen
import ch.hevs.fastandmudry.ui.components.ButtonFactory
import ch.hevs.fastandmudry.utils.Constant.CAR_SELECTOR
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.{Gdx, Input}

class CarSelectorScreen extends AbstractScreen {
  private val background: BitmapImage = new BitmapImage(CAR_SELECTOR.BACKGROUND_IMAGE)
  private val titleFont: BitmapFont = new BitmapFont()
  titleFont.getData.setScale(CAR_SELECTOR.FONT_SIZE)
  private var currentIndex: Int = CarSkins.all.indexOf(World.INSTANCE.selectedSkin)
  private def currentSkin: CarSkin = CarSkins.all(currentIndex)
  private val carImages: Array[BitmapImage] = CarSkins.all.map(skin => new BitmapImage(skin.threeQuarterImagePath)).toArray

  private val btnPrevious = ButtonFactory.primary("<")
  btnPrevious.setSize(CAR_SELECTOR.BUTTON_WIDTH, CAR_SELECTOR.BUTTON_HEIGHT)
  btnPrevious.setPosition(
    CAR_SELECTOR.SIDE_PADDING,
    (Gdx.graphics.getHeight - btnPrevious.getHeight) / 2
  )
  btnPrevious.onClick(() => previous())

  private val btnNext = ButtonFactory.primary(">")
  btnNext.setSize(CAR_SELECTOR.BUTTON_WIDTH, CAR_SELECTOR.BUTTON_HEIGHT)
  btnNext.setPosition(
    Gdx.graphics.getWidth - CAR_SELECTOR.SIDE_PADDING - btnNext.getWidth,
    (Gdx.graphics.getHeight - btnNext.getHeight) / 2
  )
  btnNext.onClick(() => next())

  private val btnSelect = ButtonFactory.primary("Choisir")
  btnSelect.setSize(CAR_SELECTOR.SELECT_BUTTON_WIDTH, CAR_SELECTOR.SELECT_BUTTON_HEIGHT)
  btnSelect.setPosition(
    (Gdx.graphics.getWidth - btnSelect.getWidth) / 2,
    CAR_SELECTOR.TOP_BOTTOM_PADDING
  )
  btnSelect.onClick(() => onSkinSelected(currentSkin))

  private val btnExit = ButtonFactory.primary("Exit")
  btnExit.setSize(CAR_SELECTOR.EXIT_BUTTON_WIDTH, CAR_SELECTOR.EXIT_BUTTON_HEIGHT)
  btnExit.setPosition(CAR_SELECTOR.SIDE_PADDING, Gdx.graphics.getHeight - btnExit.getHeight - CAR_SELECTOR.TOP_BOTTOM_PADDING)
  btnExit.onClick(() => GameStateMachine.handle(BackToMenu))

  override def onInit(): Unit = {
    AudioManager.playTrack(MusicTrack.Menu)
    Gdx.input.setInputProcessor(stage)
    stage.addActor(btnPrevious)
    stage.addActor(btnNext)
    stage.addActor(btnSelect)
    stage.addActor(btnExit)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    drawBackground(g)
    drawCar(g)
    g.drawStringCentered(CAR_SELECTOR.TOP_BOTTOM_PADDING + CAR_SELECTOR.SELECT_BUTTON_HEIGHT + 100, currentSkin.carName, titleFont)

    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      GameStateMachine.handle(BackToMenu)
    }

    renderStage(g, Gdx.graphics.getDeltaTime)
  }

  private def previous(): Unit = {
    currentIndex = (currentIndex - 1 + CarSkins.all.length) % CarSkins.all.length
  }

  private def next(): Unit = {
    currentIndex = (currentIndex + 1) % CarSkins.all.length
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

  private def drawCar(g: GdxGraphics): Unit = {
    val carImage = carImages(currentIndex)
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = carImage.getImage.getWidth.toFloat
    val imgH = carImage.getImage.getHeight.toFloat

    g.drawTransformedPicture(screenW / 2f, screenH / 2f + CAR_SELECTOR.CAR_VERTICAL_OFFSET, 0f, imgW / 2f, imgH / 2f, carImage)
  }

  private def onSkinSelected(skin: CarSkin): Unit = {
    World.INSTANCE.selectedSkin = skin
    GameStateMachine.handle(CarSkinSelected(skin))
  }
}
