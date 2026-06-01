package ch.hevs.fastandmudry
package core.ecs.entities.Item

import com.badlogic.gdx.graphics.Texture

class SimpleTree extends AItem {
  val TEXTURE = new Texture("data/images/item/SimpleTree.png")
  override def getTexture: Texture = TEXTURE
}