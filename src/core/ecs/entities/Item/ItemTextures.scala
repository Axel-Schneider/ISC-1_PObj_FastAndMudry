package ch.hevs.fastandmudry
package core.ecs.entities.Item

import ch.hevs.fastandmudry.utils.Constant.MapTexture.ITEMS
import com.badlogic.gdx.graphics.Texture

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
  object Forest {
    private lazy val SimpleTrees = getAllTexture(ITEMS.FOREST.SIMPLE_TREE.IMAGE_SOURCE)
    private lazy val SimpleRocks = getAllTexture(ITEMS.FOREST.SIMPLE_ROCK.IMAGE_SOURCE)
    private lazy val SimpleBushs = getAllTexture(ITEMS.FOREST.SIMPLE_BUSH.IMAGE_SOURCE)

    def SimpleTree: Texture = SimpleTrees(_random.nextInt(SimpleTrees.length))
    def SimpleRock: Texture = SimpleRocks(_random.nextInt(SimpleRocks.length))
    def SimpleBush: Texture = SimpleBushs(_random.nextInt(SimpleBushs.length))
  }

  object Desert {
    private lazy val Cactuss = getAllTexture(ITEMS.DESERT.CACTUS.IMAGE_SOURCE)
    private lazy val DeserticRocks = getAllTexture(ITEMS.DESERT.DESERTIC_ROCK.IMAGE_SOURCE)
    private lazy val DeserticSkeletons = getAllTexture(ITEMS.DESERT.DESERTIC_SKELETON.IMAGE_SOURCE)

    def Cactus: Texture = Cactuss(_random.nextInt(Cactuss.length))
    def DeserticRock: Texture = DeserticRocks(_random.nextInt(DeserticRocks.length))
    def DeserticSkeleton: Texture = DeserticSkeletons(_random.nextInt(DeserticSkeletons.length))
  }

  object Snow {
    private lazy val PinTrees = getAllTexture(ITEMS.SNOW.PIN_TREE.IMAGE_SOURCE)
    private lazy val SnowyRocks = getAllTexture(ITEMS.SNOW.SNOWY_ROCK.IMAGE_SOURCE)
    private lazy val SnowPiles = getAllTexture(ITEMS.SNOW.SNOW_PILE.IMAGE_SOURCE)
    def PinTree: Texture = PinTrees(_random.nextInt(PinTrees.length))
    def SnowyRock: Texture = SnowyRocks(_random.nextInt(SnowyRocks.length))
    def SnowPile: Texture = SnowPiles(_random.nextInt(SnowPiles.length))
  }

  lazy val HES = new Texture(ITEMS.HES.IMAGE_SOURCE)

}
