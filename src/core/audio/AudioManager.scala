package ch.hevs.fastandmudry
package core.audio

import utils.Constant.AUDIO

import ch.hevs.gdx2d.components.audio.VolumeMusicPlayer

import java.util

object AudioManager {
  private val players = new util.HashMap[MusicTrack, VolumeMusicPlayer]()
  private var current: MusicTrack = _

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

  def dispose(): Unit = {
    val values = players.values.iterator
    while (values.hasNext) {
      values.next.dispose()
    }
    players.clear()
    current = null
  }
}
