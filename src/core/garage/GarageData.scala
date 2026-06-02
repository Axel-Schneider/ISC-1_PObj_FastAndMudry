package ch.hevs.fastandmudry
package core.garage

import scala.collection.mutable.ArrayBuffer

final case class GarageItem(text: String, imagePath: String, buttonText: String)

object GarageData {
  val items: ArrayBuffer[GarageItem] = ArrayBuffer(
    GarageItem("Pneus percés", "data/images/item/SimpleRock.png", "Réparer"),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer"),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer"),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer"),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Moteur en surchauffe", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Niveau d'huile insuffisant", "data/images/item/SimpleRock.png", "Réparer"),
    GarageItem("Freins cassés", "data/images/item/SimpleTree.png", "Réparer"),
    GarageItem("Vitres fissurées", "data/images/item/SimpleRock.png", "Réparer")
  )
}
