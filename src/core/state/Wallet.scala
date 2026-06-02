package ch.hevs.fastandmudry
package core.state

object Wallet {
  private var _coins: Int = 0

  def coins: Int = _coins

  def add(coins: Int): Unit = {
    if (coins > 0) _coins += coins
  }

  def spend(amount: Int): Boolean = {
    if (amount > 0 && amount <= _coins) {
      _coins -= amount
      true
    } else {
      false
    }
  }

  def reset(): Unit = {
    _coins = 0
  }
}
