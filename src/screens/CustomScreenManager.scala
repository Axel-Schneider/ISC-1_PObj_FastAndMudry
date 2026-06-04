package ch.hevs.fastandmudry
package screens

import screens.cinematic.CinematicScreen
import screens.death.DeathScreen
import screens.game.GameScreen
import screens.garage.GarageScreen
import screens.loading.LoadingScreen
import screens.menu.MenuScreen
import screens.quiz.QuizScreen

import ch.hevs.fastandmudry.screens.carSelector.CarSelectorScreen
import ch.hevs.gdx2d.lib.ScreenManager

object CustomScreenManager {
  private val s = new ScreenManager
  val LOADING = 0
  val MENU = 1
  val GAME = 2
  val CINEMATIC = 3
  val QUIZ = 4
  val DEATH = 5
  val GARAGE = 6
  val CAR_SELECTOR = 7

  def getInstance: ScreenManager = s

  // Register Screen
  s.registerScreen(classOf[LoadingScreen])
  s.registerScreen(classOf[MenuScreen])
  s.registerScreen(classOf[GameScreen])
  s.registerScreen(classOf[CinematicScreen])
  s.registerScreen(classOf[QuizScreen])
  s.registerScreen(classOf[DeathScreen])
  s.registerScreen(classOf[GarageScreen])
  s.registerScreen(classOf[CarSelectorScreen])
}
