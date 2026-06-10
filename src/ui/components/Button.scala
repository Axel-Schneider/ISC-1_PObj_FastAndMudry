package ch.hevs.fastandmudry
package ui.components

import ch.hevs.fastandmudry.ui.UISkin
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, TextButton}
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class CustomButton(text: String, skin: Skin) extends TextButton(text, skin) {
  def onClick(action: () => Unit): Unit = {
    addListener(new ClickListener {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = action()
    })
  }
}

object ButtonFactory {
  def primary(text: String): CustomButton = new CustomButton(text, UISkin.skin)
}