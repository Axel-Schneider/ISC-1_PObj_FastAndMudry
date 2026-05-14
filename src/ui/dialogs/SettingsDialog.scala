package ch.hevs.fastandmudry
package ui.dialogs

import screens.CustomScreenManager
import ui.components.ButtonFactory
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class SettingsDialog(title: String, skin: Skin) extends CustomDialog(title, skin) {
  private val btnHello = ButtonFactory.primary("Hello")
  btnHello.addListener(new ClickListener {
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      val manager = CustomScreenManager.getInstance
      manager.activateScreen(CustomScreenManager.GAME)
    }
  })

  button(btnHello)
  override def getPrefWidth:  Float = Gdx.graphics.getWidth  * 0.6f
  override def getPrefHeight:  Float = Gdx.graphics.getHeight  * 0.6f

}
