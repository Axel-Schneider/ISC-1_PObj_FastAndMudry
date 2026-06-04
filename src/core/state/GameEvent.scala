package ch.hevs.fastandmudry
package core.state

import core.car.CarSkin

sealed trait GameEvent

case object StartGame extends GameEvent
case object MapLoaded extends GameEvent
case object FinishLineCrossed extends GameEvent
case object EndDayCinematicEnded extends GameEvent
case object QuizCompleted extends GameEvent
case object GarageReady extends GameEvent
case object StartDayCinematicEnded extends GameEvent
case object FinalCinematicEnded extends GameEvent
case object CarBroke extends GameEvent
case object BackToMenu extends GameEvent
case object OpenCarSelector extends GameEvent
case object OpenCarDebug extends GameEvent
final case class CarSkinSelected(skin: CarSkin) extends GameEvent
