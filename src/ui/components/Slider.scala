package ch.hevs.fastandmudry
package ui.components

import ch.hevs.fastandmudry.ui.UISkin
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, Slider}
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class CustomSlider(min: Float, max: Float, stepSize: Float, vertical: Boolean, skin: Skin) extends Slider(min, max, stepSize, vertical, skin) {
  def onChange(action: Float => Unit): Unit = {
    addListener(new ChangeListener {
      override def changed(changeEvent: ChangeListener.ChangeEvent, actor: Actor): Unit = action(getValue)
    })
  }
}

object SliderFactory {
  def create(min: Float, max: Float, stepSize: Float, vertical: Boolean): CustomSlider = new CustomSlider(min, max, stepSize, vertical, UISkin.skin)
}