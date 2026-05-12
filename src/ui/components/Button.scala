package ch.hevs.fastandmudry
package ui.components

import ui.UISkin
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, TextButton}

class CustomButton(text: String, skin: Skin) extends TextButton(text, skin){
}

object ButtonFactory {
  def primary(text: String): CustomButton = new CustomButton(text, UISkin.skin)
}