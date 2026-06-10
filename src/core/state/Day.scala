package ch.hevs.fastandmudry
package core.state

import ch.hevs.fastandmudry.core.world.biome.{Biome, DesertBiome, ForestBiome, SnowBiome}
import ch.hevs.gdx2d.components.bitmaps.BitmapImage

sealed trait Day {
  def next: Option[Day]
  def newBiome: Biome
  def endDayCinematicBackground: BitmapImage
  def startDayCinematicBackground: BitmapImage
}

case object Day1 extends Day {
  val next: Option[Day] = Some(Day2)
  def newBiome: Biome = new ForestBiome()
  def endDayCinematicBackground: BitmapImage = new BitmapImage("data/images/cinematic/day1/bg.png")
  def startDayCinematicBackground: BitmapImage = new BitmapImage("data/images/cinematic/day1/bg.png")
}

case object Day2 extends Day {
  val next: Option[Day] = Some(Day3)
  def newBiome: Biome = new DesertBiome()
  def endDayCinematicBackground: BitmapImage = new BitmapImage("data/images/cinematic/day2/bg.jpg")
  def startDayCinematicBackground: BitmapImage = new BitmapImage("data/images/cinematic/day2/bg.jpg")
}

case object Day3 extends Day {
  val next: Option[Day] = None
  def newBiome: Biome = new SnowBiome()
  def endDayCinematicBackground: BitmapImage = new BitmapImage("data/images/cinematic/day3/bg.jpg")
  def startDayCinematicBackground: BitmapImage = new BitmapImage("data/images/cinematic/day3/bg.jpg")
}
