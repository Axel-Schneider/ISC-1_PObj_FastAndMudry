package ch.hevs.fastandmudry
package core.quiz

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object QuizData {
  val QUESTIONS_PER_QUIZ_SESSION: Int = 3

  val questions: ArrayBuffer[Question] = ArrayBuffer(
    Question("Quelle est la vitesse maximale autorisee sur autoroute en Suisse ?",
      Array("120 km/h", "130 km/h", "100 km/h", "140 km/h"), 0),
    Question("Que signifie un panneau triangulaire a bord rouge ?",
      Array("Interdiction", "Danger", "Obligation", "Indication"), 1),
    Question("A un stop, vous devez :",
      Array("Ralentir seulement", "Vous arreter completement", "Klaxonner", "Accelerer"), 1),
    Question("Quelle distance de securite minimale faut-il garder ?",
      Array("Aucune", "Une longueur de voiture", "Le temps de 2 secondes", "10 metres fixes"), 2),
    Question("Que veut dire un feu orange clignotant ?",
      Array("Arret obligatoire", "Passage interdit", "Prudence, ralentir", "Demi-tour"), 2),
    Question("Qui a la priorite a un carrefour non signale ?",
      Array("Celui de gauche", "Celui de droite", "Le plus rapide", "Le plus gros vehicule"), 1),
    Question("Quand doit-on allumer les feux de croisement ?",
      Array("Jamais en ville", "La nuit et par mauvaise visibilite", "Seulement sur autoroute", "Uniquement en hiver"), 1),
    Question("Le port de la ceinture de securite est :",
      Array("Optionnel a l'arriere", "Obligatoire pour tous", "Obligatoire seulement devant", "Recommande"), 1),
    Question("Que signifie une ligne blanche continue au centre ?",
      Array("On peut depasser", "Interdiction de la franchir", "Zone de parking", "Piste cyclable"), 1),
    Question("A l'approche d'un passage pour pietons, vous devez :",
      Array("Accelerer pour passer", "Ceder le passage aux pietons", "Klaxonner", "Ne rien changer"), 1)
  )

  private val usedQuestionsText: ArrayBuffer[String] = ArrayBuffer.empty

  def nextSession(): ArrayBuffer[Question] = {
    val unusedQuestions = ArrayBuffer.empty[Question]
    for (question <- questions) {
      if (!usedQuestionsText.contains(question.text)) {
        unusedQuestions += question
      }
    }

    val picked = ArrayBuffer.empty[Question]
    var count = 0
    while (count < QUESTIONS_PER_QUIZ_SESSION) {
      if(unusedQuestions.nonEmpty){
        val r = new Random()
        val randomIndex = r.nextInt(unusedQuestions.length)
        val question = unusedQuestions(randomIndex)
        picked += question
        usedQuestionsText += question.text
        unusedQuestions.remove(randomIndex)
        count += 1
      }
    }

    picked
  }

  def reset(): Unit = {
    usedQuestionsText.clear()
  }
}
