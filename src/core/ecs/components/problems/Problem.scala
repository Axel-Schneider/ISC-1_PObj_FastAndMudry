package ch.hevs.fastandmudry.core.ecs.components.problems

import ch.hevs.fastandmudry.core.ecs.systems.Car

trait Problem {
  private var _isBroken = false;
  def IsBroken: Boolean = _isBroken
  def IsBroken_=(value: Boolean): Unit = { _isBroken = value }

  private var _Title: String = ""
  private var _ReparationPrice: Int = 0
  private var _IconPath: String = ""

  def Title: String = _Title
  protected def Title_=(value: String): Unit = {
    _Title = value
  }

  def ReparationPrice: Int = _ReparationPrice
  protected def ReparationPrice_=(value: Int): Unit = {
    _ReparationPrice = value
  }

  def IconPath: String = _IconPath
  protected def IconPath_=(value: String): Unit = {
    _IconPath = value
  }

  def impactCar(elapsedTime: Float, car: Car): Unit

  def reset(): Unit = {
    IsBroken = false;
  }
}
