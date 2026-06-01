package ch.hevs.fastandmudry
package core.ecs.systems.track

import core.ecs.components.AGameLoop
import core.ecs.systems.Car
import utils.Constant.GAME.CAR.FACTOR
import core.world.biome.{Biome, SnowBiome}

import core.state.{FinishLineCrossed, GameStateMachine}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Pixmap, PixmapIO}
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2

class Track(private val Car: Car) extends AGameLoop {
  private var geometry: TrackGeometry = _
  private var texture: Texture = _
  private var finished: Boolean = false

  var biome: Biome = new SnowBiome()

  def Geometry: TrackGeometry = geometry
  def Texture: Texture = texture

  def setBiome(biome: Biome): Unit = {
    this.biome = biome
  }

  def buildPixmap(): Pixmap = {
    geometry = new TrackGeometry(new Vector2(0f, 100f), new Vector2(5000f, 100f), 20)
    TrackTexture.generate(geometry, biome)
  }

  def installPixmap(pixmap: Pixmap): Unit = {
    // for debug
    PixmapIO.writePNG(Gdx.files.local("track_debug.png"), pixmap)

    texture = new Texture(pixmap)
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear)
    pixmap.dispose()

    val spawn = geometry.CenterLine(0)
    val end  = geometry.CenterLine(1)
    Car.reset()
    Car.Coordinates = new Vector2(spawn.x, spawn.y)
    Car.Rotation    = math.atan2(end.x - spawn.x, end.y - spawn.y).toFloat

    finished = false
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    if (geometry == null) return
    if (finished) return
    if (geometry.isFinishLine(Car.Coordinates)) {
      println("Finish line crossed!")
      finished = true
      GameStateMachine.handle(FinishLineCrossed)
      return
    }
    val isOffRoad = geometry.isOffRoad(Car.Coordinates)
    Car.MaxSpeed = if (!isOffRoad) FACTOR.MAX_SPEED else FACTOR.MAX_SPEED * biome.offRoadDecreasingFactorSpeed
    biome.updatePhysics(Car, isOffRoad, elapsedTime)
    if(Car.isBroken) {
      println("BOOM")
    }
  }
}
