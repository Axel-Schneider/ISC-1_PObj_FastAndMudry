package ch.hevs.fastandmudry
package ui.components

import ui.UISkin

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.{ProgressBar, Skin}
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class CustomProgressBar(min: Float, max: Float, stepSize: Float, vertical: Boolean, skin: Skin) extends ProgressBar(min, max, stepSize, vertical, skin) {
  def onChange(action: Float => Unit): Unit = {
    addListener(new ChangeListener {
      override def changed(changeEvent: ChangeListener.ChangeEvent, actor: Actor): Unit = action(getValue)
    })
  }
}

object ProgressBarFactory {
  def create(min: Float, max: Float, stepSize: Float, vertical: Boolean): CustomProgressBar = new CustomProgressBar(min, max, stepSize, vertical, UISkin.skin)
}