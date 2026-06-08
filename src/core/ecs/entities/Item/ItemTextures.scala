package ch.hevs.fastandmudry
package core.ecs.entities.Item

import com.badlogic.gdx.graphics.Texture
import utils.Constant.MapTexture.ITEMS

object ItemTextures {
  lazy val SimpleTree = new Texture(ITEMS.SIMPLE_TREE.IMAGE_SOURCE)
  lazy val SimpleRock = new Texture(ITEMS.SIMPLE_ROCK.IMAGE_SOURCE)
  lazy val HES = new Texture(ITEMS.HES.IMAGE_SOURCE)

}
