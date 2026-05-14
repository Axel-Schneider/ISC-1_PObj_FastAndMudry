package ch.hevs.fastandmudry
package ui.dialogs

import ui.UISkin
import com.badlogic.gdx.scenes.scene2d.ui.{Dialog, Skin}

abstract class CustomDialog(title: String, skin: Skin) extends Dialog(title, skin) {

}

object DialogFactory {
  def createSettingsDialog(title: String): SettingsDialog = new SettingsDialog(title, UISkin.skin)
}
