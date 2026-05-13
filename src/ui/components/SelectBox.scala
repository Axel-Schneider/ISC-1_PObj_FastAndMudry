package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.ui.{SelectBox, Skin}

class CustomSelectBox(styleName: String, skin: Skin) extends SelectBox(skin, styleName){
}

object SelectBoxFactory {
  def create(styleName: String): CustomSelectBox = new CustomSelectBox(styleName, UISkin.skin)
}