package ch.hevs.fastandmudry
package core.ecs.components

import utils.Constant.GAME.CAR

trait HasTires {
  private var _isRightTirePerforated: Boolean = false
  private var _isLeftTirePerforated: Boolean = false

  def IsRightTirePerforated: Boolean = _isRightTirePerforated
  def IsLeftTirePerforated: Boolean = _isLeftTirePerforated

  def IsRightTirePerforated_=(value: Boolean): Unit = {
    _isRightTirePerforated = value
  }

  def IsLeftTirePerforated_=(value: Boolean): Unit = {
    _isLeftTirePerforated = value
  }
}
