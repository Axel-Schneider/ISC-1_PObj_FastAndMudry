package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.ui.{Label, Skin}

class CustomLabel(text: String, skin: Skin) extends Label(text, skin){
}

object LabelFactory {
  def create(text: String): CustomLabel = new CustomLabel(text, UISkin.skin)
}