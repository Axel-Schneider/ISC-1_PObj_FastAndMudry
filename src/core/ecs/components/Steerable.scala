package ch.hevs.fastandmudry
package core.ecs.components

import com.badlogic.gdx.Input

trait Steerable {
  private var _wheelAngle: Float = 0f
  private var _IsSteeringWheelReturnEnable: Boolean = false

  var leftKey = Input.Keys.LEFT
  var rightKey = Input.Keys.RIGHT

  def IsSteeringWheelReturnEnable: Boolean = _IsSteeringWheelReturnEnable

  def IsSteeringWheelReturnEnable_=(value: Boolean): Unit = {
    _IsSteeringWheelReturnEnable = value
  };

  def WheelAngle: Float = _wheelAngle

  def WheelAngle_=(value: Float): Unit = {
    _wheelAngle = value
  }
}
