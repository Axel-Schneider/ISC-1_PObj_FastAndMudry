package ch.hevs.fastandmudry
package core.ecs.entities.Item.forest

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, DefectChassis}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.FOREST.SIMPLE_TREE.RENDERING_FACTOR
import com.badlogic.gdx.graphics.Texture

class SimpleTree extends AItem with CircleCollision with DefectChassis {
  this.Size = 50

  private lazy val texture = ItemTextures.Forest.SimpleTree

  override def getTexture: Texture = texture

  override def getMaxSize: (Int, Int) = (getTexture.getWidth * RENDERING_FACTOR, getTexture.getHeight * RENDERING_FACTOR)
}