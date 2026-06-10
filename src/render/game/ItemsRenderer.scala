package ch.hevs.fastandmudry
package render.game

import ch.hevs.fastandmudry.core.ecs.entities.Item.AItem
import ch.hevs.fastandmudry.core.world.World
import ch.hevs.fastandmudry.render.AbstractRenderer
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2

object ItemsRenderer extends AbstractRenderer{
  case class ProjectionResult(screenX: Float, screenY: Float, scale: Float, distance: Float)
  private case class RenderItem(item: AItem, proj: ProjectionResult)
  def projectToScreen(position: Vector2, screenW: Float, screenH: Float): Option[ProjectionResult] = {
    val cameraPosition = TrackRenderer.cameraPosition
    val alpha = TrackRenderer.cameraAngle
    val pitch = TrackRenderer.pitch
    val fov = TrackRenderer.cameraFov

    val screenPlanDistance = (0.5f / Math.atan(fov / 2.0)).toFloat

    val differentialVector = new Vector2(position.x, position.y)
    differentialVector.sub(cameraPosition.x + 0.5f, cameraPosition.y)

    val cosAlpha = Math.cos(alpha).toFloat
    val sinAlpha = Math.sin(alpha).toFloat

    val primeVector = new Vector2(
      differentialVector.x * cosAlpha - differentialVector.y * sinAlpha,
      differentialVector.x * sinAlpha + differentialVector.y * cosAlpha
    )

    // Si l'objet est derrière
    if (primeVector.y <= 0.01f) return None

    val screenX = screenW * (0.5f + (screenPlanDistance * primeVector.x) / primeVector.y)
    val screenY = screenH * (0.5f + pitch - (screenPlanDistance * cameraPosition.z) / primeVector.y)

    val scale = screenPlanDistance / primeVector.y

    Some(ProjectionResult(screenX, screenY, scale, primeVector.y))
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    World.INSTANCE.TRACK.getMapItems.flatMap((i) => {
      val coord = i.Coordinates.cpy()
      projectToScreen(coord, g.getScreenWidth.toFloat, g.getScreenHeight.toFloat).map(p => RenderItem(i, p))
    }).sortBy(_.proj.distance)(Ordering[Float].reverse).foreach(r => {
      val (mw, mh) = r.item.getMaxSize
      val rw = mw * r.proj.scale
      val rh = mh * r.proj.scale
      val rx = r.proj.screenX - rw/2f
      val ry = r.proj.screenY
      g.draw(r.item.getTexture, rx , ry, rw, rh)
    })
  }
}
