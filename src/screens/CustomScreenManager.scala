package ch.hevs.fastandmudry
package screens

import screens.game.GameScreen
import screens.loading.LoadingScreen
import screens.menu.MenuScreen

import ch.hevs.gdx2d.lib.ScreenManager

object CustomScreenManager {
  private val s = new ScreenManager
  val LOADING = 0
  val MENU = 1
  val GAME = 2
  def getInstance: ScreenManager = s

  // Register Screen
  s.registerScreen(classOf[LoadingScreen])
  s.registerScreen(classOf[MenuScreen])
  s.registerScreen(classOf[GameScreen])
}
