package ch.hevs.fastandmudry
package screens.carSelector

import core.car.{CarSkin, CarSkins}
import core.state.{BackToMenu, CarSkinSelected, GameStateMachine}
import core.world.World
import screens.AbstractScreen
import ui.components.ListItemRow
import ui.UISkin
import utils.Constant.GARAGE

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.scenes.scene2d.ui.{ScrollPane, Table}
import com.badlogic.gdx.{Gdx, Input}

class CarSelectorScreen extends AbstractScreen {
  val TITLE: String = "Select your Car"

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage)
    buildList()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawStringCentered(g.getScreenHeight * 0.90f, TITLE)

    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      GameStateMachine.handle(BackToMenu)
    }

    renderStage(g, Gdx.graphics.getDeltaTime)
  }

  private def buildList(): Unit = {
    val skinsList = new Table(UISkin.skin)

    for (skin <- CarSkins.all) {
      val row = ListItemRow.create(skin.threeQuarterImagePath, skin.pilotName, "Choisir")
      row.onClick(() => onSkinSelected(skin))
      skinsList.add(row).growX().pad(GARAGE.ROW_GAP)
      skinsList.row()
    }

    val scrollPane = new ScrollPane(skinsList, UISkin.skin)
    scrollPane.setFadeScrollBars(false)
    scrollPane.setScrollingDisabled(true, false)
    scrollPane.setSize(GARAGE.LIST_WIDTH, Gdx.graphics.getHeight - 2 * GARAGE.LIST_PADDING)
    scrollPane.setPosition(GARAGE.LIST_PADDING, GARAGE.LIST_PADDING)

    stage.addActor(scrollPane)
  }

  private def onSkinSelected(skin: CarSkin): Unit = {
    World.INSTANCE.selectedSkin = skin
    GameStateMachine.handle(CarSkinSelected(skin))
  }
}
