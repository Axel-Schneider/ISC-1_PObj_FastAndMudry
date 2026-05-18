package ch.hevs.fastandmudry
package ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin

object UISkin {
  lazy val skin: Skin = new Skin(Gdx.files.internal("data/ui/components/button/uiskin.json"))
}
