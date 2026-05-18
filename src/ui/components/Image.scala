package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.ui.{Image, Skin}

class CustomImage(drawableName: String, skin: Skin) extends Image(skin, drawableName){
}

object ImageFactory {
  def create(drawableName: String): CustomImage = new CustomImage(drawableName, UISkin.skin)
}