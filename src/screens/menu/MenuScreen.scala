package ch.hevs.fastandmudry
package screens.menu

import core.quiz.QuizData
import core.state.{GameStateMachine, OpenCarDebug, OpenCarSelector, StartGame, Wallet}
import screens.AbstractScreen
import ui.components.ButtonFactory
import ui.dialogs.{DialogFactory, SettingsDialog}

import utils.Constant.MENU
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}

class MenuScreen extends AbstractScreen {
  private val background: BitmapImage = new BitmapImage(MENU.BACKGROUND_IMAGE)
  private val BUTTON_WIDTH = 300
  private val BUTTON_HEIGHT = 80
  private val xButtonPosition = (Gdx.graphics.getWidth - BUTTON_WIDTH)  / 2
  private val yButtonPosition = (Gdx.graphics.getHeight - BUTTON_HEIGHT) / 3

  private val btnPlay = ButtonFactory.primary("Play")
  btnPlay.setSize(BUTTON_WIDTH, BUTTON_HEIGHT)
  btnPlay.setPosition(xButtonPosition, yButtonPosition)
  btnPlay.onClick(() => {
    QuizData.reset()
    Wallet.reset()
    GameStateMachine.handle(StartGame)
  })

  private val settingsDialog: SettingsDialog = DialogFactory.createSettingsDialog("Settings")

  private val btnSettings = ButtonFactory.primary("Settings")
  btnSettings.setSize(BUTTON_WIDTH, BUTTON_HEIGHT)
  btnSettings.setPosition(xButtonPosition, yButtonPosition - 100)
  btnSettings.onClick(() => settingsDialog.show(stage))

  private val btnCarSelector = ButtonFactory.primary("Car selector")
  btnCarSelector.setSize(BUTTON_WIDTH, BUTTON_HEIGHT)
  btnCarSelector.setPosition(xButtonPosition, yButtonPosition - 200)
  btnCarSelector.onClick(() => GameStateMachine.handle(OpenCarSelector))

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage) // send inputs events to the stage
    stage.addActor(btnPlay)
    stage.addActor(btnSettings)
    stage.addActor(btnCarSelector)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    drawBackground(g)

    if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
      GameStateMachine.handle(OpenCarDebug)
    }

    renderStage(g, Gdx.graphics.getDeltaTime)
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
}
