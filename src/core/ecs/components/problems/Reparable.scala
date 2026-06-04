package ch.hevs.fastandmudry.core.ecs.components.problems

trait Reparable extends Problem {
  private var _Title: String = ""
  private var _ReparationPrice: Int = 0
  private var _IconPath: String = "data/images/item/SimpleRock.png"
  private var _ButtonText: String = "Repare"

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

  def ButtonText: String = _ButtonText
  private def ButtonText_=(value: String): Unit = {
    _ButtonText = value
  }

}
