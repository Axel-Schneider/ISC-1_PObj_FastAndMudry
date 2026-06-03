package ch.hevs.fastandmudry
package core.ecs.entities.Item

import ch.hevs.fastandmudry.core.ecs.components.Collision.CircleCollision
import ch.hevs.fastandmudry.core.world.World
import com.badlogic.gdx.graphics.Texture
import utils.Constant.MapTexture.ITEMS.SIMPLE_TREE.RENDERING_FACTOR

class SimpleTree extends AItem with CircleCollision {
  this.Size = 50
  override def getTexture: Texture = ItemTextures.SimpleTree
  override def getMaxSize: (Int, Int) = (getTexture.getWidth * RENDERING_FACTOR, getTexture.getHeight * RENDERING_FACTOR)
  override def onCollision(): Unit = {
//    World.INSTANCE.CAR.
    println("Car hit a tree")
  }
}