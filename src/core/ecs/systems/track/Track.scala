package ch.hevs.fastandmudry
package core.ecs.systems.track

import core.ecs.components.AGameLoop
import core.ecs.systems.{Car, MapGenerator}

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.PixmapIO
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.math.Vector2

class Track(private val Car: Car) extends AGameLoop {
  private var geometry: TrackGeometry = _
  private var texture: Texture = _

  def Geometry: TrackGeometry = geometry
  def Texture: Texture = texture

  def generateNewMap(): Unit = {
    geometry = new TrackGeometry(new Vector2(0f, 100f), new Vector2(1000f, 100f), 8, 30)
    val pixmap = TrackTexture.generate(geometry)

    // for debug
    PixmapIO.writePNG(Gdx.files.local("track_debug.png"), pixmap)

    texture = new Texture(pixmap)
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
    pixmap.dispose()

    val spawn = geometry.CenterLine(0)
    val end  = geometry.CenterLine(1)
    Car.Coordinates = new Vector2(spawn.x, spawn.y)
    Car.Rotation    = math.atan2(end.x - spawn.x, end.y - spawn.y).toFloat
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    // Checkup game interaction between car and track
  }
}
