package ch.hevs.fastandmudry
package render.game

import render.AbstractRenderer

import core.world.World
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.math.{Vector2, Vector3}


class TrackRenderer extends AbstractRenderer {
  var imageBackground : BitmapImage = null
  var fbo: FrameBuffer = null
  val OUT_SIDE_COLOR = Array[Float](1f,0f,0f,0.5f)

  var cameraFov = Math.PI.toFloat / 4f;
  var cameraAngle = 0f
  val cameraPosition = new Vector3(-0.1f, 0.06f, 0.5f)
  val cameraAxis = new Vector3(0f, -0f, -0.1f)
  var pitch = 0f

  override def onInit(): Unit = {
    imageBackground = new BitmapImage("data/images/img.png")
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    graphicalSetup()

    if(fbo == null) {
      fbo = new FrameBuffer(Format.RGBA8888, imageBackground.getImage.getWidth, imageBackground.getImage.getHeight, false)
      fbo.begin()
      g.clear()
      g.drawPicture(imageBackground.getImage.getWidth/2, imageBackground.getImage.getHeight/2, imageBackground)
      g.sbFlush()
      fbo.end()
    }

    cameraPosition.y = World.INSTANCE.CAR.Coordinates.y
    cameraPosition.x = World.INSTANCE.CAR.Coordinates.x
    cameraAngle = World.INSTANCE.CAR.Rotation
    if(g.getShaderRenderer == null) {
      g.setShader("data/shaders/perspective.glsl")
    }

    g.getShaderRenderer.setTexture(fbo.getColorBufferTexture, 0)
    g.getShaderRenderer.setUniform("enabled", true)
    g.getShaderRenderer.setUniform("cameraPosition", cameraPosition)
    g.getShaderRenderer.setUniform("cameraAxis", cameraAxis)
    g.getShaderRenderer.setUniform("screenPlanDistance", 0.5f / Math.atan(cameraFov / 2.0).toFloat)
    g.getShaderRenderer.setUniform("cameraAngle", cameraAngle)
    g.getShaderRenderer.setUniform("resolution", new Vector2(g.getScreenWidth.toFloat, g.getScreenHeight.toFloat))
    g.getShaderRenderer.setUniform("pitch", pitch)

    g.drawShader()
  }

  def graphicalSetup() = {
    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    if(Gdx.input.isKeyPressed(Input.Keys.Q)) cameraFov += 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.A)) cameraFov -= 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.W)) pitch += 0.01f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.S)) pitch -= 0.01f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.E)) cameraAxis.z += 1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.D)) cameraAxis.z -= 1f * ELAPSED_TIME

    if(Gdx.input.isKeyPressed(Input.Keys.T)) cameraPosition.z -= 0.1f * ELAPSED_TIME
    if(Gdx.input.isKeyPressed(Input.Keys.G)) cameraPosition.z += 0.1f * ELAPSED_TIME


    if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
      println("=============")
      println(cameraPosition)
      println(cameraAxis)
      println(cameraFov)
      println(cameraAngle)
      println("=============")
    }

  }
}
