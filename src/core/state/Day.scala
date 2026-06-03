package ch.hevs.fastandmudry
package core.state

import core.world.biome.{Biome, DesertBiome, ForestBiome, SnowBiome}

sealed trait Day {
  def next: Option[Day]
  def newBiome: Biome
}

case object Day1 extends Day {
  val next: Option[Day] = Some(Day2)
  def newBiome: Biome = new ForestBiome()
}

case object Day2 extends Day {
  val next: Option[Day] = Some(Day3)
  def newBiome: Biome = new DesertBiome()
}

case object Day3 extends Day {
  val next: Option[Day] = None
  def newBiome: Biome = new SnowBiome()
}
