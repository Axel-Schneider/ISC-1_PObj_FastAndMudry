package ch.hevs.fastandmudry
package core.ecs.entities.Item

import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS.HES.RENDERING_FACTOR
import com.badlogic.gdx.graphics.Texture


class HES extends AItem {
  override def getTexture: Texture = ItemTextures.HES
  override def getMaxSize: (Int, Int) = (getTexture.getWidth*RENDERING_FACTOR, getTexture.getHeight*RENDERING_FACTOR)
}
