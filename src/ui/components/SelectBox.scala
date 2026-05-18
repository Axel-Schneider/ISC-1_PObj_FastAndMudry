package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.{SelectBox, Skin}
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class CustomSelectBox(styleName: String, skin: Skin) extends SelectBox(skin, styleName) {
  def onChange[T](action: T => Unit): Unit = {
    addListener(new ChangeListener {
      override def changed(changeEvent: ChangeListener.ChangeEvent, actor: Actor): Unit = action(getSelected)
    })
  }
}

object SelectBoxFactory {
  def create(styleName: String): CustomSelectBox = new CustomSelectBox(styleName, UISkin.skin)
}