package ch.hevs.fastandmudry
package screens.game

import core.world.World
import render.WorldRenderer
import screens.AbstractScreen

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.Texture.TextureWrap
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.{Vector2, Vector3}

class GameScreen extends AbstractScreen {
  val OUT_SIDE_COLOR = new Vector3(1f,0f,0f)
  var imageBackground : BitmapImage = null
  var fbo: FrameBuffer = null
  var shaderEnabled = true

  override def onInit(): Unit = {
    imageBackground = new BitmapImage("data/images/img.png")
    w = imageBackground.getImage.getWidth
    h = imageBackground.getImage.getHeight
  }

  var cameraFov = Math.PI.toFloat / 6.0f;
  var cameraAngle = 0f
  val cameraPosition = new Vector3(-0.1f, 0.06f, 0.01f)
  val cameraAxis = new Vector3(0f, -0f, -0.8f)
  var (w, h) = (0, 0)


  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    if(fbo == null) {
      fbo = new FrameBuffer(Format.RGBA8888, imageBackground.getImage.getWidth, imageBackground.getImage.getHeight, false)
      fbo.begin()
      g.clear()
      g.drawPicture(imageBackground.getImage.getWidth/2, imageBackground.getImage.getHeight/2, imageBackground)
      g.sbFlush()
      fbo.end()
    }

    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    if(Gdx.input.isKeyPressed(Input.Keys.Q)) cameraFov += 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.A)) cameraFov -= 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.W)) w -= 10
    if(Gdx.input.isKeyPressed(Input.Keys.S)) w += 10

    if(Gdx.input.isKeyPressed(Input.Keys.E)) h -= 10
    if(Gdx.input.isKeyPressed(Input.Keys.D)) h += 10


    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) cameraAngle -= 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) cameraAngle += 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.T)) cameraPosition.z -= 0.1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.G)) cameraPosition.z += 0.1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
      cameraPosition.y += math.cos(cameraAngle).toFloat * 0.1f * ELAPSED_TIME
      cameraPosition.x += math.sin(cameraAngle).toFloat * 0.1f * ELAPSED_TIME
    }
    if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      cameraPosition.y -= math.cos(cameraAngle).toFloat * 0.1f * ELAPSED_TIME
      cameraPosition.x -= math.sin(cameraAngle).toFloat * 0.1f * ELAPSED_TIME
    }

    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
      println("=============")
      println(cameraPosition)
      println(cameraAxis)
      println(cameraFov)
      println(cameraAngle)
      println("=============")
    }

    // Rendering
    if(g.getShaderRenderer == null) {
      g.setShader("data/shaders/perspective.glsl")
    }


    g.getShaderRenderer.setTexture(fbo.getColorBufferTexture, 0)
    g.getShaderRenderer.setUniform("backbuffer", 0)
    g.getShaderRenderer.setUniform("enabled", shaderEnabled)
    g.getShaderRenderer.setUniform("cameraPosition", cameraPosition)
    g.getShaderRenderer.setUniform("cameraAxis", cameraAxis)
    g.getShaderRenderer.setUniform("screenPlanDistance", 0.5f / Math.atan(cameraFov / 2.0).toFloat)
    g.getShaderRenderer.setUniform("cameraAngle", cameraAngle.toFloat)
    g.getShaderRenderer.setUniform("resolution", new Vector2(g.getScreenWidth.toFloat, g.getScreenHeight.toFloat))
    g.getShaderRenderer.setUniform("outSideColor", OUT_SIDE_COLOR)

    g.drawShader()
    g.end()
  }
}
