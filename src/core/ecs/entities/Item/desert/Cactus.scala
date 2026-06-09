package ch.hevs.fastandmudry.core.ecs.entities.Item.desert

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, DefectChassis}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.DESERT.CACTUS
import com.badlogic.gdx.graphics.Texture

class Cactus extends AItem with CircleCollision with DefectChassis {
  this.Size = 50

  private lazy val texture = ItemTextures.Desert.Cactus

  override def getTexture: Texture = texture

  override def getMaxSize: (Int, Int) = (getTexture.getWidth * CACTUS.RENDERING_FACTOR, getTexture.getHeight * CACTUS.RENDERING_FACTOR)
}
