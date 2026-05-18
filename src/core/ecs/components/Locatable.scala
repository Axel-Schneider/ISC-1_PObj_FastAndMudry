package ch.hevs.fastandmudry
package core.ecs.components

import com.badlogic.gdx.math.Vector2

trait Locatable {
  private var _Coordinates: Vector2 = new Vector2(0f, 0f)

  def Coordinates: Vector2 = _Coordinates

  def Coordinates_=(value: Vector2): Unit = {
    _Coordinates = value
  }
}
