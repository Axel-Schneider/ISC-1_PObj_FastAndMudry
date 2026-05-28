package ch.hevs.fastandmudry
package render.game

import core.world.World
import render.AbstractRenderer
import render.shaders.Mode7
import utils.Common
import ch.hevs.fastandmudry.ui.hud.DebugHUD

import ch.hevs.fastandmudry.utils.Constant.MapTexture
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, Input}


class TrackRenderer extends AbstractRenderer {
  var imageBackground : BitmapImage = null
  var fbo: FrameBuffer = null

  private var cameraFov = Mode7.DEFAULT_VALUES.CAMERA.FOV
  private var cameraAngle = Mode7.DEFAULT_VALUES.CAMERA.ANGLE
  private val cameraPosition =  Mode7.DEFAULT_VALUES.CAMERA.POSITION
  private val cameraAxis =  Mode7.DEFAULT_VALUES.CAMERA.AXIS
  private var pitch = Mode7.DEFAULT_VALUES.PITCH
  private val renderingFactor = new Vector2(0.01f, 0.01f)

  override def onInit(): Unit = {
    val texture = World.INSTANCE.TRACK.Texture
    renderingFactor.set(1f / texture.getWidth, 1f / texture.getHeight)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    graphicalSetup()

    cameraPosition.y = World.INSTANCE.CAR.Coordinates.y
    cameraPosition.x = World.INSTANCE.CAR.Coordinates.x
    cameraAngle = World.INSTANCE.CAR.Rotation

    // rendering

    if(g.getShaderRenderer == null) {
      g.setShader(Mode7.SHADER_PATH)
    }

    g.getShaderRenderer.setTexture(World.INSTANCE.TRACK.Texture, 0)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.ENABLE, Mode7.DEFAULT_VALUES.ENABLE)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.POSITION, cameraPosition)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.AXIS, cameraAxis)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.CAMERA.ANGLE, cameraAngle)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.SCREEN.PLAN.DISTANCE, 0.5f / Math.atan(cameraFov / 2.0).toFloat)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.RESOLUTION, new Vector2(g.getScreenWidth.toFloat, g.getScreenHeight.toFloat))
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.PITCH, pitch)
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.RENDERING_FACTOR, renderingFactor)

    val trackRectangle = World.INSTANCE.TRACK.Geometry.trackSize
    g.getShaderRenderer.setUniform(Mode7.Parameter.KEY.MAP_ORIGIN, new Vector2(trackRectangle.x - MapTexture.MAP_PADDING, trackRectangle.y - MapTexture.MAP_PADDING))

    g.drawShader()
  }

  private def graphicalSetup(): Unit = {
    val ELAPSED_TIME = Gdx.graphics.getDeltaTime;

    if (!Common.Debugging.IsDebugEnable) return;

    cameraFov = updateValue(cameraFov, Input.Keys.Q, Input.Keys.A, 1f, ELAPSED_TIME);
    pitch = updateValue(pitch, Input.Keys.W, Input.Keys.S, 0.01f, ELAPSED_TIME);
    cameraPosition.z = updateValue(cameraPosition.z, Input.Keys.T, Input.Keys.G, 0.1f, ELAPSED_TIME);

    DebugHUD.setLogVar("Track Render - Camera Position (+T, -G)", cameraPosition.toString)
    DebugHUD.setLogVar("Track Render - Camera FOV (+Q, -A)", cameraFov)
    DebugHUD.setLogVar("Track Render - Pitch (+W, -S)", pitch)
    DebugHUD.setLogVar("Track Render - RenderingFactor", renderingFactor.toString)
  }

  private def updateValue(v: Float, keyAdd: Int, keySub: Int, factor: Float, elapsedTime: Float): Float = {
    var r = v
    if(Gdx.input.isKeyPressed(keySub)) r -= factor * elapsedTime
    if(Gdx.input.isKeyPressed(keyAdd)) r += factor * elapsedTime
    r
  }
}
