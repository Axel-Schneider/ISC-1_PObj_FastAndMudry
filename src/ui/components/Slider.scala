package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.ui.{Slider, Skin}

class CustomSlider(min: Float, max: Float, stepSize: Float, vertical: Boolean, skin: Skin) extends Slider(min, max, stepSize, vertical, skin){
}

object SliderFactory {
  def create(min: Float, max: Float, stepSize: Float, vertical: Boolean): CustomSlider = new CustomSlider(min, max, stepSize, vertical, UISkin.skin)
}