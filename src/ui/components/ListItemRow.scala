package ch.hevs.fastandmudry
package ui.components

import ch.hevs.fastandmudry.ui.UISkin
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.{Color, Texture}
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Skin, Table}
import com.badlogic.gdx.scenes.scene2d.utils.{ClickListener, TextureRegionDrawable}

class ListItemRow(imagePath: String, text: String, buttonText: String, skin: Skin) extends Table(skin) {

  private val texture = new Texture(com.badlogic.gdx.Gdx.files.internal(imagePath))
  private val image = new Image(new TextureRegionDrawable(new TextureRegion(texture)))
  private val button = new CustomButton(buttonText, skin)

  add(image).size(ListItemRow.IMAGE_SIZE).pad(ListItemRow.PADDING)
  add(text, "default").expandX().left().pad(ListItemRow.PADDING)
  add(button).pad(ListItemRow.PADDING)

  def onClick(action: () => Unit): Unit = {
    button.addListener(new ClickListener {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        if(!button.isDisabled) action()
      }
    })
  }

  def setDisabled(value: Boolean): Unit = {
    println(s"$text - diable : $value")
    button.setDisabled(value)
    button.setColor(if (value) Color.DARK_GRAY else Color.LIGHT_GRAY)
  }
}

object ListItemRow {
  val IMAGE_SIZE: Float = 64f
  val PADDING: Float = 10f

  def create(imagePath: String, text: String, buttonText: String): ListItemRow =
    new ListItemRow(imagePath, text, buttonText, UISkin.skin)
}
