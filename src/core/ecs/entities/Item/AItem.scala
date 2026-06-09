package ch.hevs.fastandmudry
package core.ecs.entities.Item

import ch.hevs.fastandmudry.core.ecs.components.Collision.Collisional
import ch.hevs.fastandmudry.core.ecs.systems.track.TrackGeometry
import ch.hevs.fastandmudry.core.world.World
import com.badlogic.gdx.graphics.Texture
import core.ecs.components.Locatable

abstract class AItem extends Locatable {
  private var isColliding: Boolean = false

  def isOnTrackImportant = true
  def getIsOnTrack: Boolean = false
  def getTexture: Texture
  def getMaxSize: (Int, Int) = (getTexture.getWidth, getTexture.getHeight)
  def checkStats(): Unit = {
    if(isInstanceOf[Collisional]) {
      val collisionnable = this.asInstanceOf[Collisional]
      val collides = collisionnable.CheckCollision(World.INSTANCE.CAR)
      if(collides && !isColliding) collisionnable.onCollision(World.INSTANCE.CAR)
      isColliding = collides
    }
  }

  final def isGenerationAllowed(geometry: TrackGeometry): Boolean = {
    isOnTrackImportant && geometry.isOffRoad(this.Coordinates) == getIsOnTrack
  }
}
