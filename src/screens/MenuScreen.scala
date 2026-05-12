package ch.hevs.fastandmudry
package screens

import ui.components.ButtonFactory

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.InputEvent

class MenuScreen extends AbstractScreen {
  private val btnPlay = ButtonFactory.primary("Play")
  btnPlay.setSize(300, 80)
  btnPlay.setPosition(
    (Gdx.graphics.getWidth  - btnPlay.getWidth)  / 2,
    (Gdx.graphics.getHeight - btnPlay.getHeight) / 2
  )
  btnPlay.addListener(new ClickListener {
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        val manager = CustomScreenManager.getInstance
        manager.activateScreen(CustomScreenManager.GAME)
    }
  })

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage) // send inputs events to the stage
    stage.addActor(btnPlay)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {g.clear()
    g.clear()
    g.drawStringCentered(g.getScreenHeight - 50, "BIENVENUE SUR FAST & MUDRY !")
    renderStage(g, Gdx.graphics.getDeltaTime)
  }
}
