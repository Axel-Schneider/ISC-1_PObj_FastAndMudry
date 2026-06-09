package ch.hevs.fastandmudry.core.ecs.entities.Item.snow

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, DefectChassis}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.SNOW.PIN_TREE
import com.badlogic.gdx.graphics.Texture

class PinTree extends AItem with CircleCollision with DefectChassis {
  this.Size = 50

  private lazy val texture = ItemTextures.Snow.PinTree

  override def getTexture: Texture = texture

  override def getMaxSize: (Int, Int) = (getTexture.getWidth * PIN_TREE.RENDERING_FACTOR, getTexture.getHeight * PIN_TREE.RENDERING_FACTOR)
}
