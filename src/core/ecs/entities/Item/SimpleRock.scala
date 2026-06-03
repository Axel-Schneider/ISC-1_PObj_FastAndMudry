package ch.hevs.fastandmudry.core.ecs.entities.Item

import ch.hevs.fastandmudry.core.ecs.components.Collision.{Collisionnable, PassThoughtCollision}
import ch.hevs.fastandmudry.core.ecs.components.Locatable
import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.SIMPLE_ROCK.RENDERING_FACTOR
import com.badlogic.gdx.graphics.Texture

class SimpleRock extends AItem {
  override def getTexture: Texture = ItemTextures.SimpleRock
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*RENDERING_FACTOR, getTexture.getHeight*RENDERING_FACTOR)
}