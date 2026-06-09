package ch.hevs.fastandmudry.core.ecs.entities.Item.snow

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, NoDefect}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.SNOW.SNOWY_ROCK
import com.badlogic.gdx.graphics.Texture

class SnowyRock extends AItem with CircleCollision with NoDefect {
  override def isOnTrackImportant: Boolean = false
  override def getIsOnTrack: Boolean = true
  private lazy val texture = ItemTextures.Snow.SnowyRock
  override def getTexture: Texture = texture
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*SNOWY_ROCK.RENDERING_FACTOR, getTexture.getHeight*SNOWY_ROCK.RENDERING_FACTOR)
}