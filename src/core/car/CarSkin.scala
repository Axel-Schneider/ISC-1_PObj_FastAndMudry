package ch.hevs.fastandmudry
package core.car

import scala.collection.mutable.ArrayBuffer

final case class WheelPosition(x: Float, y: Float)

final case class CarSkin(pilotName: String, carName: String,
                         threeQuarterImagePath: String, sideImagePath: String, openHoodImagePath: String,
                         interiorImagePath: String, steeringWheelImagePath: String,
                         wheelPosition: WheelPosition)

object CarSkins {
  private val prefix = "data/images/skins/"
  private val threeQuarterImagePathSuffix = "/three_quarter.png"
  private val sideImagePathSuffix = "/side.png"
  private val openHoodImagePathSuffix = "/open_hood.png"
  private val interiorImagePathSuffix = "/interior.png"
  private val steeringWheelImagePathSuffix = "/steering_wheel.png"

  val all: ArrayBuffer[CarSkin] = ArrayBuffer(
    CarSkin("Default", "BMW E36 M3",
      prefix + "e36" + threeQuarterImagePathSuffix, prefix + "e36" + sideImagePathSuffix, prefix + "e36" + openHoodImagePathSuffix,
      prefix + "e36" + interiorImagePathSuffix, prefix + "e36" + steeringWheelImagePathSuffix,
      WheelPosition(0.35f, 0.10f),
    ),
    CarSkin("Mudry", "Renault Zoe",
      prefix + "zoe" + threeQuarterImagePathSuffix, prefix + "zoe" + sideImagePathSuffix, prefix + "zoe" + openHoodImagePathSuffix,
      prefix + "zoe" + interiorImagePathSuffix, prefix + "zoe" + steeringWheelImagePathSuffix,
      WheelPosition(0.50f, 0.1f),
    ),
  )

  val default: CarSkin = all(0)
}
