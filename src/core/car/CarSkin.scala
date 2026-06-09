package ch.hevs.fastandmudry
package core.car

import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ArrayBuffer

final case class WheelPosition(x: Float, y: Float)

final case class SpeedometerConfig(x: Float, y: Float, radius: Float,
                                   backgroundColor: Color, ticksColor: Color, needleColor: Color)

final case class CarSkin(pilotName: String, carName: String,
                         threeQuarterImagePath: String, sideImagePath: String, openHoodImagePath: String,
                         interiorImagePath: String, steeringWheelImagePath: String,
                         wheelPosition: WheelPosition,
                         speedometer: SpeedometerConfig)

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
      SpeedometerConfig(0.295f, 0.22f, 100f, Color.BLACK, Color.WHITE, Color.RED),
    ),
    CarSkin("Mudry", "Renault Zoe",
      prefix + "zoe" + threeQuarterImagePathSuffix, prefix + "zoe" + sideImagePathSuffix, prefix + "zoe" + openHoodImagePathSuffix,
      prefix + "zoe" + interiorImagePathSuffix, prefix + "zoe" + steeringWheelImagePathSuffix,
      WheelPosition(0.50f, 0.1f),
      SpeedometerConfig(0.4f, 0.15f, 100f, Color.BLACK, Color.WHITE, Color.CYAN),
    ),
    CarSkin("Ryan Gosling", "Chevrolet Chevelle Malibu 1973",
      prefix + "chevrolet" + threeQuarterImagePathSuffix, prefix + "chevrolet" + sideImagePathSuffix, prefix + "chevrolet" + openHoodImagePathSuffix,
      prefix + "chevrolet" + interiorImagePathSuffix, prefix + "chevrolet" + steeringWheelImagePathSuffix,
      WheelPosition(0.35f, 0.18f),
      SpeedometerConfig(0.28f, 0.19f, 80f, Color.BLACK, Color.WHITE, Color.RED),
    ),
    CarSkin("Jacquemet", "Car Postal",
      prefix + "bus" + threeQuarterImagePathSuffix, prefix + "bus" + sideImagePathSuffix, prefix + "bus" + openHoodImagePathSuffix,
      prefix + "bus" + interiorImagePathSuffix, prefix + "bus" + steeringWheelImagePathSuffix,
      WheelPosition(0.19f, -0.1f),
      SpeedometerConfig(0.1f, 0f, 80f, Color.BLACK, Color.WHITE, Color.RED),
    ),
  )

  val default: CarSkin = all(0)
}
