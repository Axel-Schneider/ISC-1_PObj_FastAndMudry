package ch.hevs.fastandmudry
package ui.components

import ui.UISkin
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, ProgressBar}

class CustomProgressBar(min: Float, max: Float, stepSize: Float, vertical: Boolean, skin: Skin) extends ProgressBar(min, max, stepSize, vertical, skin){
}

object ProgressBarFactory {
  def create(min: Float, max: Float, stepSize: Float, vertical: Boolean): CustomProgressBar = new CustomProgressBar(min, max, stepSize, vertical, UISkin.skin)
}