package ch.hevs.fastandmudry
package core.audio

import utils.Constant.AUDIO.MUSIC

sealed trait MusicTrack {
  def path: String
}

object MusicTrack {
  case object Menu extends MusicTrack { val path: String = MUSIC.MENU }
  case object Cinematic extends MusicTrack { val path: String = MUSIC.CINEMATIC }
  case object Quiz extends MusicTrack { val path: String = MUSIC.QUIZ }
  case object Garage extends MusicTrack { val path: String = MUSIC.GARAGE }
  case object Forest extends MusicTrack { val path: String = MUSIC.FOREST }
  case object Desert extends MusicTrack { val path: String = MUSIC.DESERT }
  case object Snow extends MusicTrack { val path: String = MUSIC.SNOW }
}
