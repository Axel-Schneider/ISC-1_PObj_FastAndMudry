package ch.hevs.fastandmudry
package screens.garage

import core.garage.GarageData
import core.state.{GameStateMachine, GarageReady}
import screens.AbstractScreen
import ui.components.ListItemRow
import ui.UISkin
import utils.Constant.GARAGE

import ch.hevs.fastandmudry.core.world.World
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.scenes.scene2d.ui.{ScrollPane, Table}
import com.badlogic.gdx.{Gdx, Input}

class GarageScreen extends AbstractScreen {
  val BOTTOM_MESSAGE: String = "Réparez votre voiture"
  private val background: BitmapImage = new BitmapImage(GARAGE.BACKGROUND_IMAGE)
  private val carImage: BitmapImage = new BitmapImage(World.INSTANCE.selectedSkin.openHoodImagePath)

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage)
    buildList()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    drawBackground(g)
    drawCar(g)
    g.drawStringCentered(g.getScreenHeight * 0.06f, BOTTOM_MESSAGE)

    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      GameStateMachine.handle(GarageReady)
    }

    renderStage(g, Gdx.graphics.getDeltaTime)
  }

  private def drawBackground(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = background.getImage.getWidth.toFloat
    val imgH = background.getImage.getHeight.toFloat

    val scale = math.min(screenW / imgW, screenH / imgH)
    val drawW = imgW * scale
    val drawH = imgH * scale

    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, drawW / 2f, drawH / 2f, background)
  }

  private def drawCar(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = carImage.getImage.getWidth.toFloat
    val imgH = carImage.getImage.getHeight.toFloat

    g.drawTransformedPicture((screenW+screenW/2) / 2f, (screenH-screenH/4) / 2f, 0f, imgW / 2f, imgH / 2f, carImage)
  }

  private def buildList(): Unit = {
    val repairsList = new Table(UISkin.skin)

    for (item <- GarageData.items) {
      val row = ListItemRow.create(item.imagePath, item.text, s"${item.buttonText} - ${item.price}.-")
      row.onClick(() => onItemClicked(item.text))
      repairsList.add(row).growX().pad(GARAGE.ROW_GAP)
      repairsList.row() // line break
    }

    val scrollPane = new ScrollPane(repairsList, UISkin.skin)
    scrollPane.setFadeScrollBars(false)
    scrollPane.setScrollingDisabled(true, false)
    scrollPane.setSize(GARAGE.LIST_WIDTH, Gdx.graphics.getHeight - 2 * GARAGE.LIST_PADDING)
    scrollPane.setPosition(GARAGE.LIST_PADDING, GARAGE.LIST_PADDING)

    stage.addActor(scrollPane)
  }

  private def onItemClicked(text: String): Unit = {
    println(s"Item: $text clicked")
  }
}
