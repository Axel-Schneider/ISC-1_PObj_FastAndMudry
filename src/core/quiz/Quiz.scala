package ch.hevs.fastandmudry
package core.quiz

import scala.collection.mutable.ArrayBuffer

sealed trait QuizPhase
object QuizPhase {
  case object ShowingQuestion extends QuizPhase
  case object ShowingAnswers extends QuizPhase
  case object Revealing extends QuizPhase
}

class Quiz(questions: ArrayBuffer[Question]) {
  private var index: Int = 0
  private var phase: QuizPhase = QuizPhase.ShowingQuestion
  private var _isLastAnswerCorrect: Boolean = false

  def getPhase: QuizPhase = phase
  def currentQuestion: Question = questions(index)
  def isLastAnswerCorrect: Boolean = _isLastAnswerCorrect

  def revealAnswers(): Unit = {
    if (phase == QuizPhase.ShowingQuestion) phase = QuizPhase.ShowingAnswers
  }

  def answer(chosenIndex: Int): Boolean = {
    _isLastAnswerCorrect = chosenIndex == currentQuestion.correctIndex
    phase = QuizPhase.Revealing
    isLastAnswerCorrect
  }

  def doContinueQuestions(): Boolean = {
    if (index < questions.length - 1) {
      index += 1
      phase = QuizPhase.ShowingQuestion
      return true
    } else {
      return false
    }
  }
}
