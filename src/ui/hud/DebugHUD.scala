package ch.hevs.fastandmudry
package ui.hud

import utils.Common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}

import java.util

object DebugHUD {
  val batch = new SpriteBatch()
  val font = new BitmapFont()

  private val logs = new util.HashMap[String, Any]()

  def draw(): Unit = {
    if(!Common.Debugging.IsDebugEnable) return
    batch.begin()
    var i = 30;
    font.draw(batch, s"FPS: ${Gdx.graphics.getFramesPerSecond}", 10, Gdx.graphics.getHeight - 10)
    logs.forEach((k, v) => {
      font.draw(batch, s"$k: ${v.toString}", 10, Gdx.graphics.getHeight - i)
      i += 20
    })
    batch.end()
  }

  def dispose(): Unit = {
    batch.dispose()
    font.dispose()
  }

  def setLogVar(name: String, value: Any): Unit = {
    logs.put(name, value)
  }
}
