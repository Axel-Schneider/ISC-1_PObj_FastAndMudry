package ch.hevs.fastandmudry
package core.ecs.entities.Item

import com.badlogic.gdx.graphics.Texture
import utils.Constant.MapTexture.ITEMS

import java.io.File
import scala.util.Random


object ItemTextures {
  private lazy val _random = new Random()
  private def getAllTexture(src: String): List[Texture] = {
    val folder = new File(src)
    folder.listFiles()
      .filter(file => file.isFile && file.getName.toLowerCase.endsWith(".png"))
      .map[Texture](f => new Texture(f.getPath)).toList
  }
  private lazy val SimpleTrees = getAllTexture(ITEMS.SIMPLE_TREE.IMAGE_SOURCE)
  private lazy val SimpleRocks = getAllTexture(ITEMS.SIMPLE_ROCK.IMAGE_SOURCE)
  private lazy val SimpleBushs = getAllTexture(ITEMS.SIMPLE_BUSH.IMAGE_SOURCE)

  def SimpleTree: Texture = SimpleTrees(_random.nextInt(SimpleTrees.length))
  def SimpleRock: Texture = SimpleRocks(_random.nextInt(SimpleRocks.length))
  def SimpleBush: Texture = SimpleBushs(_random.nextInt(SimpleBushs.length))
  lazy val HES = new Texture(ITEMS.HES.IMAGE_SOURCE)

}
