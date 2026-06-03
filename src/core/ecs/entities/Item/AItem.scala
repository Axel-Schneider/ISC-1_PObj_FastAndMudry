package ch.hevs.fastandmudry
package core.ecs.entities.Item

import ch.hevs.fastandmudry.core.ecs.components.Collision.Collisionnable
import ch.hevs.fastandmudry.core.world.World
import com.badlogic.gdx.graphics.Texture
import core.ecs.components.Locatable

abstract class AItem extends Locatable {
  def getTexture: Texture
  def getMaxSize: (Int, Int) = (getTexture.getWidth, getTexture.getHeight)
  def checkStats(): Unit = {
    if(isInstanceOf[Collisionnable]) {
      val collisionnable = this.asInstanceOf[Collisionnable]
      if(collisionnable.CheckCollision(World.INSTANCE.CAR)) collisionnable.onCollision()
    }
  }
}
