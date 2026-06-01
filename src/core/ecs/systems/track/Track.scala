package ch.hevs.fastandmudry
package core.ecs.systems.track

import core.ecs.components.AGameLoop
import core.ecs.systems.Car
import utils.Constant.GAME.CAR.FACTOR
import core.world.biome.{Biome, DesertBiome, ForestBiome, SnowBiome}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, SimpleRock, SimpleTree}
import ch.hevs.fastandmudry.utils.Constant.MapTexture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Pixmap, PixmapIO}
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2

import scala.util.Random

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
    Car.Coordinates = new Vector2(spawn.x, spawn.y)
    Car.Rotation    = math.atan2(end.x - spawn.x, end.y - spawn.y).toFloat

    generateMapItems()
    finished = false
  }

  override def onGameLoop(elapsedTime: Float): Unit = {
    if (geometry == null || finished) return
    if (geometry.isFinishLine(Car.Coordinates)) {
      println("Finish line crossed!")
      finished = true
    }
    val isOffRoad = geometry.isOffRoad(Car.Coordinates)
    Car.MaxSpeed = if (!isOffRoad) FACTOR.MAX_SPEED else FACTOR.MAX_SPEED * biome.offRoadDecreasingFactorSpeed
    biome.updatePhysics(Car, isOffRoad, elapsedTime)
    if(Car.isBroken) {
      println("BOOM")
    }
  }

  private var mapItems = List[AItem]()
  def generateMapItems(): Unit = {
    val random = new Random()

    // Récupération des limites de la carte basées sur ton architecture
    val trackWidth = Geometry.trackSize.getWidth + MapTexture.MAP_PADDING * 2
    val trackHeight = Geometry.trackSize.getHeight + MapTexture.MAP_PADDING * 2

    // List.fill exécute le bloc de code 50 fois et retourne une List immuable
    mapItems = List.fill(500) {

      val tree = if(random.nextInt(2) % 2 == 0) new SimpleTree() else new SimpleRock()

      // nextFloat() génère une valeur entre 0.0f et 1.0f
      tree.Coordinates.x = random.nextFloat() * trackWidth
      tree.Coordinates.y = random.nextFloat() * trackHeight

      tree
    }
  }

  def getMapItems: List[AItem] = mapItems
}

