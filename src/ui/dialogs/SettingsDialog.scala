package ch.hevs.fastandmudry
package ui.dialogs

import ch.hevs.fastandmudry.ui.components.{ButtonFactory, LabelFactory, SliderFactory}
import ch.hevs.fastandmudry.utils.Constant.UI
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class SettingsDialog(title: String, skin: Skin) extends CustomDialog(title, skin) {
  private val lblVolumeText = LabelFactory.create("Volume")
  private val lblVolumeValue = LabelFactory.create("50")
  private val sldVolume = SliderFactory.create(0f, 100f, 1f, false)
  sldVolume.setValue(50f)
  sldVolume.onChange(volume => lblVolumeValue.setText(volume.toInt.toString))

  pad(20)

  private val content = getContentTable
  content.clear()
  content.center()
  content.add(lblVolumeText).padRight(15)
  content.add(sldVolume).expandX().fillX().padRight(15)
  content.add(lblVolumeValue).width(40)

  private val btnSave = ButtonFactory.primary("Save")
  private val btnCancel = ButtonFactory.primary("Cancel")
  btnSave.onClick(() => hide())
  btnCancel.onClick(() => hide())
  button(btnCancel)
  button(btnSave)

  override def getPrefWidth:  Float = Gdx.graphics.getWidth  * UI.Dialog.SettingsDialog.SIZE_PERCENTAGE
  override def getPrefHeight: Float = Gdx.graphics.getHeight * UI.Dialog.SettingsDialog.SIZE_PERCENTAGE
}
