package ch.hevs.fastandmudry
package core.quiz

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object QuizData {
  val QUESTIONS_PER_QUIZ_SESSION: Int = 3

  val questions: ArrayBuffer[Question] = ArrayBuffer(
    Question("Quelle est la capitale de l'Australie ?",
      Array("Sydney", "Melbourne", "Canberra", "Perth"), 2),
    Question("En quelle annee a eu lieu la chute du mur de Berlin ?",
      Array("1987", "1989", "1991", "1985"), 1),
    Question("Quel pays compte le plus grand nombre de fuseaux horaires ?",
      Array("Russie", "Etats-Unis", "Chine", "France"), 3),
    Question("Combien de cordes possede un violon ?",
      Array("4", "5", "6", "7"), 0),
    Question("Quel pays a remporte la Coupe du Monde de football 2018 ?",
      Array("Croatie", "France", "Bresil", "Allemagne"), 1),
    Question("Quel artiste a peint 'La Nuit etoilee' ?",
      Array("Claude Monet", "Vincent van Gogh", "Paul Cezanne", "Edgar Degas"), 1),
    Question("Quelle est la plus haute montagne du monde ?",
      Array("K2", "Mont Blanc", "Everest", "Kilimandjaro"), 2),
    Question("Quel est le plus grand ocean du monde ?",
      Array("Atlantique", "Indien", "Arctique", "Pacifique"), 3),
    Question("En quelle annee l'homme a-t-il marche sur la Lune pour la premiere fois ?",
      Array("1965", "1969", "1972", "1959"), 1),
    Question("Quelle ville a accueilli les premiers Jeux Olympiques modernes en 1896 ?",
      Array("Paris", "Londres", "Athenes", "Rome"), 2),

    Question("Que signifie l'acronyme HTML ?",
      Array("HyperText Markup Language", "High Tech Modern Language", "HyperText Machine Language", "Home Tool Markup Language"), 0),
    Question("Quel symbole est utilise pour les commentaires sur une ligne en Java ?",
      Array("#", "//", "<!--", "%%"), 1),
    Question("Quelle structure de donnees fonctionne en FIFO (First In First Out) ?",
      Array("Pile (Stack)", "File (Queue)", "Arbre", "Tableau"), 1),
    Question("En binaire, que vaut le nombre decimal 5 ?",
      Array("100", "111", "101", "110"), 2),
    Question("Quel langage est principalement utilise pour styliser des pages web ?",
      Array("HTML", "CSS", "SQL", "PHP"), 1),
    Question("Quelle est la complexite temporelle d'une recherche dichotomique ?",
      Array("O(n)", "O(n^2)", "O(log n)", "O(1)"), 2),
    Question("Que retourne l'expression 7 % 3 dans la plupart des langages ?",
      Array("2", "1", "3", "0"), 1),
    Question("Quel mot-cle declare une constante en Scala ?",
      Array("var", "let", "val", "const"), 2),
    Question("Quel protocole securise est utilise pour le web (HTTPS) ?",
      Array("FTP", "SSH", "TLS/SSL", "SMTP"), 2),
    Question("En programmation orientee objet, quel concept consiste a masquer les details internes ?",
      Array("Heritage", "Encapsulation", "Polymorphisme", "Abstraction"), 1)
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
