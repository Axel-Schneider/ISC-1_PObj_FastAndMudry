package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.{CheckBox, Skin}
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class CustomCheckBox(text: String, skin: Skin) extends CheckBox(text, skin) {
  def onChange(action: Boolean => Unit): Unit = {
    addListener(new ChangeListener {
      override def changed(changeEvent: ChangeListener.ChangeEvent, actor: Actor): Unit = action(isChecked)
    })
  }
}

object CheckBoxFactory {
  def create(text: String): CustomCheckBox = new CustomCheckBox(text, UISkin.skin)
}