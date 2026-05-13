package ch.hevs.fastandmudry
package ui.components

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup

class CustomButtonGroup(buttons: Array[CustomButton]) extends ButtonGroup[CustomButton]() {
  buttons.foreach(b => {
    add(b)
  })
}

object ButtonGroupFactory {
  def create(buttons: Array[CustomButton]): CustomButtonGroup = new CustomButtonGroup(buttons)
}