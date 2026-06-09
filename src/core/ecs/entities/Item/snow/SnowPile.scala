package ch.hevs.fastandmudry.core.ecs.entities.Item.snow

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, NoDefect}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.SNOW.SNOW_PILE
import com.badlogic.gdx.graphics.Texture

class SnowPile extends AItem with CircleCollision with NoDefect {
  this.Size = 25
  override def isOnTrackImportant: Boolean = false
  private lazy val texture = ItemTextures.Snow.SnowPile
  override def getTexture: Texture = texture
  override def getMaxSize: (Int, Int) = (getTexture.getWidth * SNOW_PILE.RENDERING_FACTOR, getTexture.getHeight * SNOW_PILE.RENDERING_FACTOR)
}
