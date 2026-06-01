package ch.hevs.fastandmudry
package core.ecs.entities.Item

import com.badlogic.gdx.graphics.Texture
import core.ecs.components.Locatable

abstract class AItem extends Locatable {
  def getTexture: Texture
}
