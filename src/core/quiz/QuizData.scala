package ch.hevs.fastandmudry
package core.quiz

import scala.collection.mutable.ArrayBuffer

object QuizData {
  val questions: ArrayBuffer[Question] = ArrayBuffer(
    Question("Question 1 ?", Array("CORRECT", "WRONG", "WRONG", "WRONG"), 0),
    Question("Question 2 ?", Array("WRONG", "CORRECT", "WRONG", "WRONG"), 1),
    Question("Question 3 ?", Array("WRONG", "WRONG", "WRONG", "CORRECT"), 3)
  )
}
