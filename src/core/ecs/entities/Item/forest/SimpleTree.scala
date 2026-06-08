package ch.hevs.fastandmudry
package core.ecs.entities.Item.forest

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, DefectChassis}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.core.ecs.systems.Car
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.SIMPLE_TREE.RENDERING_FACTOR
import com.badlogic.gdx.graphics.Texture

class SimpleTree extends AItem with CircleCollision with DefectChassis {
  this.Size = 50

  override def getTexture: Texture = ItemTextures.SimpleTree

  override def getMaxSize: (Int, Int) = (getTexture.getWidth * RENDERING_FACTOR, getTexture.getHeight * RENDERING_FACTOR)
}