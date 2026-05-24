package ch.hevs.fastandmudry
package core.ecs.systems.track

import core.ecs.components.AGameLoop
import core.ecs.systems.{Car, MapGenerator}

import ch.hevs.fastandmudry.core.ecs.systems
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.PixmapIO
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.math.Vector2

class Track(private val Car: Car) extends AGameLoop {
  private var geometry: TrackGeometry = _
  private var texture: Texture = _
  private var finished: Boolean = false

  def Geometry: TrackGeometry = geometry
  def Texture: Texture = texture

  def generateNewMap(): Unit = {
    geometry = new TrackGeometry(new Vector2(0f, 100f), new Vector2(5000f, 100f), 20, 30)
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

    finished = false
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    if (geometry == null || finished) return
    if (geometry.isFinishLine(Car.Coordinates)) {
      println("Finish line crossed!")
      finished = true
    }
    Car.MaxSpeed = if (geometry.isRoad(Car.Coordinates)) systems.Car.MAX_SPEED else systems.Car.MAX_SPEED * 0.5f
  }
}
