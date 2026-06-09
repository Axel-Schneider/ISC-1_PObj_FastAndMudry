package ch.hevs.fastandmudry
package core.audio

import utils.Constant.AUDIO

import ch.hevs.gdx2d.components.audio.{SoundSample, VolumeMusicPlayer}

import java.util
import scala.util.Random

object AudioManager {
  private val players = new util.HashMap[MusicTrack, VolumeMusicPlayer]()
  private var current: MusicTrack = _

  private var engine: SoundSample = _
  private var engineId: Long = -1

  private val random = new Random()
  private var backfire: SoundSample = _

  private var popsLeft: Int = 0
  private var nextPopTimer: Float = 0f

  private var tireExplosion: SoundSample = _
  private var collision: SoundSample = _

  private def playerFor(track: MusicTrack): VolumeMusicPlayer = {
    var player = players.get(track)
    if (player == null) {
      player = new VolumeMusicPlayer(track.path)
      player.setVolume(AUDIO.MUSIC_VOLUME)
      players.put(track, player)
    }
    return player
  }

  def playTrack(track: MusicTrack): Unit = {
    if (current == track) return
    if (current != null) players.get(current).stop()
    playerFor(track).loop()
    current = track
  }

  def stop(): Unit = {
    if (current != null) players.get(current).stop()
    current = null
  }

  def startEngine(): Unit = {
    if (engine == null) {
      engine = new SoundSample(AUDIO.SFX.ENGINE)
      engine.setVolume(AUDIO.SFX.ENGINE_VOLUME)
    }
    if (engineId == -1) {
      engine.setPitch(AUDIO.SFX.ENGINE_LOW_PITCH)
      engineId = engine.loop()
    }
  }

  def updateEngine(speed: Float, maxSpeed: Float): Unit = {
    if (engineId == -1) return

    var ratio = Math.abs(speed) / maxSpeed
    if (ratio > 1f) ratio = 1f

    var pitch = AUDIO.SFX.ENGINE_LOW_PITCH + ratio * (AUDIO.SFX.ENGINE_TOP_PITCH - AUDIO.SFX.ENGINE_LOW_PITCH)
    if (pitch < 0.5f) pitch = 0.5f
    if (pitch > 2f) pitch = 2f

    engine.modifyPitch(pitch, engineId)
  }

  def stopEngine(): Unit = {
    if (engine != null) engine.stop()
    engineId = -1
  }

  private def playPop(): Unit = {
    val pitchRange = AUDIO.SFX.BACKFIRE_MAX_PITCH - AUDIO.SFX.BACKFIRE_MIN_PITCH
    backfire.setPitch(AUDIO.SFX.BACKFIRE_MIN_PITCH + random.nextFloat() * pitchRange)
    backfire.play()
  }

  private def scheduleNextPop(): Unit = {
    val gapRange = AUDIO.SFX.BACKFIRE_MAX_GAP - AUDIO.SFX.BACKFIRE_MIN_GAP
    nextPopTimer = AUDIO.SFX.BACKFIRE_MIN_GAP + random.nextFloat() * gapRange
  }

  def triggerBackfire(): Unit = {
    if (backfire == null) {
      backfire = new SoundSample(AUDIO.SFX.BACKFIRE)
      backfire.setVolume(AUDIO.SFX.BACKFIRE_VOLUME)
    }

    val popCount = AUDIO.SFX.BACKFIRE_MIN_POPS + random.nextInt(AUDIO.SFX.BACKFIRE_MAX_POPS - AUDIO.SFX.BACKFIRE_MIN_POPS + 1)

    playPop()
    popsLeft = popCount - 1
    scheduleNextPop()
  }

  def updateBackfire(elapsedTime: Float): Unit = {
    if (popsLeft <= 0) return

    nextPopTimer -= elapsedTime
    if (nextPopTimer <= 0f) {
      playPop()
      popsLeft -= 1
      if (popsLeft > 0) scheduleNextPop()
    }
  }

  def playTireExplosion(): Unit = {
    if (tireExplosion == null) {
      tireExplosion = new SoundSample(AUDIO.SFX.TIRE_EXPLOSION)
      tireExplosion.setVolume(AUDIO.SFX.TIRE_EXPLOSION_VOLUME)
    }
    tireExplosion.play()
  }

  def playCollision(): Unit = {
    if (collision == null) {
      collision = new SoundSample(AUDIO.SFX.COLLISION)
      collision.setVolume(AUDIO.SFX.COLLISION_VOLUME)
    }
    collision.play()
  }

  def dispose(): Unit = {
    val values = players.values.iterator
    while (values.hasNext) {
      values.next.dispose()
    }
    players.clear()
    current = null

    if (engine != null) {
      engine.dispose()
      engine = null
    }
    engineId = -1

    if (backfire != null) {
      backfire.dispose()
      backfire = null
    }
    popsLeft = 0

    if (tireExplosion != null) {
      tireExplosion.dispose()
      tireExplosion = null
    }

    if (collision != null) {
      collision.dispose()
      collision = null
    }
  }
}
