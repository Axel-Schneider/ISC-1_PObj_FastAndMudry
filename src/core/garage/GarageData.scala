package ch.hevs.fastandmudry
package core.garage

import scala.collection.mutable.ArrayBuffer

final case class GarageItem(text: String, imagePath: String, buttonText: String, price: Int)

object GarageData {
  val items: ArrayBuffer[GarageItem] = ArrayBuffer(
    GarageItem("Pneus percés", "data/images/item/SimpleRock.png", "Réparer", 200),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer", 200),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer", 200),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer", 200),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer", 200),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer", 200),
    GarageItem("Vitres fissurées", "data/images/item/SimpleRock.png", "Réparer", 200)
  )
}
