package ch.hevs.fastandmudry
package core.ecs.components

import ch.hevs.fastandmudry.utils.Constant.GAME.CAR

trait Temperable {
  private var _temperature: Float = 0f
  private var _minTemp: Float = CAR.FACTOR.MIN_TEMPERATURE
  private var _maxTemp: Float = CAR.FACTOR.MAX_TEMPERATURE

  def Temperature: Float = _temperature
  def MinTemperature: Float = _minTemp
  def MaxTemperature: Float = _maxTemp

  def Temperature_=(value: Float): Unit = {
    _temperature = value
  }
}
