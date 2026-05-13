package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.ui.{CheckBox, Skin}

class CustomCheckBox(text: String, skin: Skin) extends CheckBox(text, skin){
}

object CheckBoxFactory {
  def create(text: String): CustomCheckBox = new CustomCheckBox(text, UISkin.skin)
}