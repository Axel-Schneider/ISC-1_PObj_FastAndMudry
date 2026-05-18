package ch.hevs.fastandmudry
package render.game

import render.AbstractRenderer
import core.world.World
import render.shaders.Mode7
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.math.{Vector2, Vector3}


class TrackRenderer extends AbstractRenderer {
  var imageBackground : BitmapImage = null
  var fbo: FrameBuffer = null

  private var cameraFov = Mode7.DEFAULT_VALUES.CAMERA.FOV
  private var cameraAngle = Mode7.DEFAULT_VALUES.CAMERA.ANGLE
  private val cameraPosition =  Mode7.DEFAULT_VALUES.CAMERA.POSITION
  private val cameraAxis =  Mode7.DEFAULT_VALUES.CAMERA.AXIS
  private var pitch = Mode7.DEFAULT_VALUES.PITCH

  // DEBUGING
  private var isLogKeyPressing = false

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

    // rendering

    if(g.getShaderRenderer == null) {
      g.setShader(Mode7.SHADER_PATH)
    }

    g.getShaderRenderer.setTexture(fbo.getColorBufferTexture, 0)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.ENABLE, Mode7.DEFAULT_VALUES.ENABLE)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.POSITION, cameraPosition)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.AXIS, cameraAxis)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.ANGLE, cameraAngle)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.SCREEN.PLAN.DISTANCE, 0.5f / Math.atan(cameraFov / 2.0).toFloat)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.RESOLUTION, new Vector2(g.getScreenWidth.toFloat, g.getScreenHeight.toFloat))
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.PITCH, pitch)

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

    cameraFov = updateValue(cameraFov, Input.Keys.Q, Input.Keys.A, 1f, ELAPSED_TIME);
    pitch = updateValue(pitch, Input.Keys.W, Input.Keys.S, 0.01f, ELAPSED_TIME);
    cameraPosition.z = updateValue(cameraPosition.z, Input.Keys.T, Input.Keys.G, 0.1f, ELAPSED_TIME);

    if (Gdx.input.isKeyPressed(Input.Keys.F11)) {
      if (!isLogKeyPressing) {
        isLogKeyPressing = true
        println("=============")
        println(s"cameraPosition : $cameraPosition")
        println(s"cameraFov : $cameraFov")
        println(s"pitch : $pitch")
        println("=============")
      }
    } else isLogKeyPressing = false
  }

  private def updateValue(v: Float, keyAdd: Int, keySub: Int, factor: Float, elapsedTime: Float): Float = {
    var r = v
    if(Gdx.input.isKeyPressed(keySub)) r -= factor * elapsedTime
    if(Gdx.input.isKeyPressed(keyAdd)) r += factor * elapsedTime
    r
  }
}
