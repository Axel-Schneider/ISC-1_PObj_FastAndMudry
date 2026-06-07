package ch.hevs.fastandmudry
package screens.menu

import core.quiz.QuizData
import core.state.{GameStateMachine, OpenCarDebug, OpenCarSelector, StartGame, Wallet}
import screens.AbstractScreen
import ui.components.ButtonFactory
import ui.dialogs.{DialogFactory, SettingsDialog}

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}

class MenuScreen extends AbstractScreen {
  private val btnPlay = ButtonFactory.primary("Play")
  btnPlay.setSize(300, 80)
  btnPlay.setPosition(
    (Gdx.graphics.getWidth  - btnPlay.getWidth)  / 2,
    (Gdx.graphics.getHeight - btnPlay.getHeight) / 2
  )
  btnPlay.onClick(() => {
    QuizData.reset()
    Wallet.reset()
    GameStateMachine.handle(StartGame)
  })

  private val settingsDialog: SettingsDialog = DialogFactory.createSettingsDialog("Settings")

  private val btnSettings = ButtonFactory.primary("Settings")
  btnSettings.setSize(300, 80)
  btnSettings.setPosition(
    (Gdx.graphics.getWidth  - btnSettings.getWidth)  / 2,
    (Gdx.graphics.getHeight - btnSettings.getHeight) / 2 - 100
  )
  btnSettings.onClick(() => settingsDialog.show(stage))

  private val btnCarSelector = ButtonFactory.primary("Car selector")
  btnCarSelector.setSize(300, 80)
  btnCarSelector.setPosition(
    (Gdx.graphics.getWidth  - btnCarSelector.getWidth)  / 2,
    (Gdx.graphics.getHeight - btnCarSelector.getHeight) / 2 - 200
  )
  btnCarSelector.onClick(() => GameStateMachine.handle(OpenCarSelector))

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage) // send inputs events to the stage
    stage.addActor(btnPlay)
    stage.addActor(btnSettings)
    stage.addActor(btnCarSelector)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawStringCentered(g.getScreenHeight - 50, "BIENVENUE SUR FAST & MUDRY !")

    if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
      GameStateMachine.handle(OpenCarDebug)
    }

    renderStage(g, Gdx.graphics.getDeltaTime)
  }
}
