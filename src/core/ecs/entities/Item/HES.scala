package ch.hevs.fastandmudry
package core.ecs.entities.Item

import com.badlogic.gdx.graphics.Texture
import utils.Constant.MapTexture.ITEMS.HES.RENDERING_FACTOR


class HES extends AItem {
  override def getTexture: Texture = ItemTextures.HES
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*RENDERING_FACTOR, getTexture.getHeight*RENDERING_FACTOR)
}
