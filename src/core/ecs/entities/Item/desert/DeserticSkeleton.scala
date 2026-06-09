package ch.hevs.fastandmudry.core.ecs.entities.Item.desert

import ch.hevs.fastandmudry.core.ecs.components.Collision.{CircleCollision, NoDefect}
import ch.hevs.fastandmudry.core.ecs.entities.Item.{AItem, ItemTextures}
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.DESERT.DESERTIC_SKELETON
import com.badlogic.gdx.graphics.Texture

class DeserticSkeleton extends AItem with CircleCollision with NoDefect {
  override def getIsOnTrack: Boolean = false
  private lazy val texture = ItemTextures.Desert.DeserticSkeleton
  override def getTexture: Texture = texture
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*DESERTIC_SKELETON.RENDERING_FACTOR, getTexture.getHeight*DESERTIC_SKELETON.RENDERING_FACTOR)
}
