package ch.hevs.fastandmudry.core.ecs.entities.Item.forest

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, NoDefect}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.SIMPLE_BUSH
import com.badlogic.gdx.graphics.Texture

class SimpleBush extends AItem with CircleCollision with NoDefect {
  this.Size = 25
  private lazy val texture = ItemTextures.SimpleBush
  override def getTexture: Texture = texture
  override def getMaxSize: (Int, Int) = (getTexture.getWidth * SIMPLE_BUSH.RENDERING_FACTOR, getTexture.getHeight * SIMPLE_BUSH.RENDERING_FACTOR)
}
