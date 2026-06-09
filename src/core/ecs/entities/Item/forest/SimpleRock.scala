package ch.hevs.fastandmudry
package core.ecs.entities.Item.forest

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, NoDefect}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.FOREST.SIMPLE_ROCK.RENDERING_FACTOR
import com.badlogic.gdx.graphics.Texture

class SimpleRock extends AItem with CircleCollision with NoDefect {
  override def isOnTrackImportant: Boolean = false
  override def getIsOnTrack: Boolean = true
  private lazy val texture = ItemTextures.Forest.SimpleRock
  override def getTexture: Texture = texture
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*RENDERING_FACTOR, getTexture.getHeight*RENDERING_FACTOR)
}