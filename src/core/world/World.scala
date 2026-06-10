package ch.hevs.fastandmudry
package core.world

import ch.hevs.fastandmudry.core.audio.AudioManager
import ch.hevs.fastandmudry.core.car.{CarSkin, CarSkins}
import ch.hevs.fastandmudry.core.ecs.components.AGameLoop
import ch.hevs.fastandmudry.core.ecs.systems.Car
import ch.hevs.fastandmudry.core.ecs.systems.track.Track
import ch.hevs.fastandmudry.utils.Constant.{AUDIO, GAME}

class World private () extends AGameLoop {
  val CAR: Car = new Car
  val TRACK: Track = new Track(CAR)

  var selectedSkin: CarSkin = CarSkins.default

  private var previousSpeed: Float = 0f
  private var backfireArmed: Boolean = false

  override def onGameLoop(elapsedTime: Float): Unit = {
    CAR.onGameLoop(elapsedTime)
    CAR.Moving(elapsedTime, GAME.CAR.FACTOR.SPEED)

    AudioManager.updateEngine(CAR.Speed, GAME.CAR.FACTOR.MAX_SPEED)
    checkBackfire()
    AudioManager.updateBackfire(elapsedTime)

    TRACK.onGameLoop(elapsedTime)
  }

  private def checkBackfire(): Unit = {
    val speed = Math.abs(CAR.Speed)
    val previous = Math.abs(previousSpeed)
    val speedLimitForBackfire = GAME.CAR.FACTOR.MAX_SPEED * AUDIO.SFX.BACKFIRE_SPEED_RATIO

    // have to load the backfire -> needs to increase speed
    if (speed > previous && speed >= speedLimitForBackfire) backfireArmed = true

    // backfire armed and speed lower than previous speed and bigger than the limit for pops -> triggers bakfire
    if (backfireArmed && speed < previous && previous >= speedLimitForBackfire) {
      AudioManager.triggerBackfire()
      backfireArmed = false
    }

    previousSpeed = CAR.Speed
  }
}

object World {
  private lazy val _Instance: World = new World

  def INSTANCE: World = _Instance
}
