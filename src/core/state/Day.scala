package ch.hevs.fastandmudry
package core.state

sealed trait Day {
  def next: Option[Day]
}

case object Day1 extends Day {
  val next: Option[Day] = Some(Day2)
}

case object Day2 extends Day {
  val next: Option[Day] = Some(Day3)
}

case object Day3 extends Day {
  val next: Option[Day] = None
}
