package ch.hevs.gdx2d.components.audio

class VolumeMusicPlayer(file: String) extends MusicPlayer(file) {
  override def setVolume(v: Float): Unit = {
    super.setVolume(v)
    s.setVolume(v)
  }
}
