package ch.hevs.fastandmudry
package core.ecs.entities.Item

import com.badlogic.gdx.graphics.Texture
import utils.Constant.MapTexture.ITEMS.SIMPLE_TREE.RENDERING_FACTOR

class SimpleTree extends AItem {
  override def getTexture: Texture = ItemTextures.SimpleTree
  override def getMaxSize: (Int, Int) = (getTexture.getWidth * RENDERING_FACTOR, getTexture.getHeight * RENDERING_FACTOR)
}