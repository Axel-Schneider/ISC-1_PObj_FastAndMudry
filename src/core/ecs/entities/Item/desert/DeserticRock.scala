package ch.hevs.fastandmudry.core.ecs.entities.Item.desert

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, NoDefect}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.DESERT.DESERTIC_ROCK
import com.badlogic.gdx.graphics.Texture

class DeserticRock extends AItem with CircleCollision with NoDefect {
  override def isOnTrackImportant: Boolean = false
  override def getIsOnTrack: Boolean = true
  private lazy val texture = ItemTextures.Desert.DeserticRock
  override def getTexture: Texture = texture
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*DESERTIC_ROCK.RENDERING_FACTOR, getTexture.getHeight*DESERTIC_ROCK.RENDERING_FACTOR)
}
