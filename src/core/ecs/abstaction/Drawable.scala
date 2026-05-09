package ch.hevs.fastandmudry
package core.ecs.abstaction

import ch.hevs.gdx2d.lib.GdxGraphics

abstract class Drawable {
  def draw(g: GdxGraphics, x: Float, y: Float);
}
