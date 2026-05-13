package ch.hevs.fastandmudry
package screens.game

import core.world.World
import render.WorldRenderer
import screens.AbstractScreen

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}

import scala.collection.mutable.ArrayBuffer

class GameScreen extends AbstractScreen {
  lazy val MAP_TEXTURE = generateMode7Ground(1024)

  override def onInit(): Unit = {  }

  var WorldX = 1000f;
  var WorldY = 1000f;
  var WorldA = 0.1f;
  var Near = 0.005f;
  var Far = 0.03f;
  var FoVHalf = 3.14159f / 4f;
  val tempColor = new Color()
  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    if(Gdx.input.isKeyPressed(Input.Keys.Q)) Near += 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.A)) Near -= 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.W)) Far += 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.S)) Far -= 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.E)) FoVHalf += 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.D)) FoVHalf -= 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) WorldA -= 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) WorldA += 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
      WorldX += math.cos(WorldA).toFloat * 20f * ELAPSED_TIME
      WorldY += math.sin(WorldA).toFloat * 20f * ELAPSED_TIME
    }
    if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      WorldX -= math.cos(WorldA).toFloat * 20f * ELAPSED_TIME
      WorldY -= math.sin(WorldA).toFloat * 20f * ELAPSED_TIME
    }

    // Rendering

    val farX1 = WorldX + Math.cos(WorldA - FoVHalf).toFloat * Far
    val farY1 = WorldY + Math.sin(WorldA - FoVHalf).toFloat * Far

    val nearX1 = WorldX + Math.cos(WorldA - FoVHalf).toFloat * Near
    val nearY1 = WorldY + Math.sin(WorldA - FoVHalf).toFloat * Near

    val farX2 = WorldX + Math.cos(WorldA + FoVHalf).toFloat * Far
    val farY2 = WorldY + Math.sin(WorldA + FoVHalf).toFloat * Far

    val nearX2 = WorldX + Math.cos(WorldA + FoVHalf).toFloat * Near
    val nearY2 = WorldY + Math.sin(WorldA + FoVHalf).toFloat * Near

    for (y <- 0 to g.getScreenHeight / 2) {
      val sampleDepth = (y / g.getScreenHeight.toFloat / 2f)

      val startX = (farX1 - nearX1) / sampleDepth + nearX1
      val startY = (farY1 - nearY1) / sampleDepth + nearY1
      val endX = (farX2 - nearX2) / sampleDepth + nearX2
      val endY = (farY2 - nearY2) / sampleDepth + nearY2

      for (x <- 0 to g.getScreenWidth) {
        val sampleWidth = x.toFloat / g.getScreenWidth
        val sampleX = ((endX - startX) * sampleWidth + startX)
        val sampleY = ((endY - startY) * sampleWidth + startY)

        val rgba8888 = MAP_TEXTURE.getPixel(sampleX.toInt, sampleY.toInt)
        Color.rgba8888ToColor(tempColor, rgba8888)


        g.setPixel(x, g.getScreenHeight/2-y, tempColor)
      }
    }
  }

  // Generation d'une map de ligne pour les tests (code de Gemini)
  def generateMode7Ground(mapSize: Int): Pixmap = {
    // Création d'un buffer de pixels en mémoire (RGBA8888)
    val pixmap = new Pixmap(mapSize, mapSize, Pixmap.Format.RGBA8888)

    // Couleurs au format libGDX
    val magenta = new Color(1f, 0f, 1f, 1f) // FG_MAGENTA
    val blue    = new Color(0f, 0f, 1f, 1f) // FG_BLUE

    // On parcourt la map avec un pas de 32 pour créer la grille
    for (i <- 0 to mapSize by 32) {
      for (j <- 0 until mapSize) {

        // Lignes verticales (Magenta) - épaisseur de 3 pixels comme dans ton code
        pixmap.setColor(magenta)
        pixmap.drawPixel(i, j)     // x
        pixmap.drawPixel(i + 1, j) // x + 1
        pixmap.drawPixel(i - 1, j) // x - 1

        // Lignes horizontales (Bleu) - épaisseur de 3 pixels
        pixmap.setColor(blue)
        pixmap.drawPixel(j, i)     // y, x
        pixmap.drawPixel(j, i + 1) // y, x + 1
        pixmap.drawPixel(j, i - 1) // y, x - 1
      }
    }
//
//    // Conversion du Pixmap (CPU) en Texture (GPU) pour l'affichage
//    val texture = new Texture(pixmap)
//
//    // Libération de la mémoire RAM du Pixmap une fois transféré au GPU
//    pixmap.dispose()
//
//    texture
    pixmap
  }
}
