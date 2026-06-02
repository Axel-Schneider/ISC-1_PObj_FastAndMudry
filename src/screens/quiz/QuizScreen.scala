package ch.hevs.fastandmudry
package screens.quiz

import core.quiz.{Quiz, QuizData, QuizPhase}
import core.state.{GameStateMachine, QuizCompleted}
import screens.AbstractScreen
import ui.components.{ButtonFactory, CustomButton}

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.{Gdx, Input}

class QuizScreen extends AbstractScreen {
  private val REVEAL_DURATION: Float = 3f

  private val BUTTON_WIDTH: Float = 300
  private val BUTTON_HEIGHT: Float = 80
  private val GRID_GAP: Float = 30

  private var quiz: Quiz = _
  private val background: BitmapImage = new BitmapImage("data/images/quiz/bg.png")
  private var revealTimer: Float = 0f
  private var buttons: Array[CustomButton] = Array.empty

  override def onInit(): Unit = {
    Gdx.input.setInputProcessor(stage)
    quiz = new Quiz(QuizData.nextSession())
    revealTimer = 0f
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    drawBackground(g)
    g.drawStringCentered(g.getScreenHeight * 0.15f, quiz.currentQuestion.text)

    quiz.getPhase match {
      case QuizPhase.ShowingQuestion => {
        g.drawStringCentered(g.getScreenHeight * 0.08f, "Press SPACE")
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
          quiz.revealAnswers()
          buildAnswerButtons()
        }
      }
      case QuizPhase.ShowingAnswers => // nothing, the click on button does the job
      case QuizPhase.Revealing => {
        revealTimer += Gdx.graphics.getDeltaTime
        if (revealTimer >= REVEAL_DURATION) {
          clearAnswerButtons()
          if (!quiz.doContinueQuestions()) GameStateMachine.handle(QuizCompleted)
        }
      }
    }

    renderStage(g, Gdx.graphics.getDeltaTime)
  }

  private def drawBackground(g: GdxGraphics): Unit = {
    val screenW = g.getScreenWidth.toFloat
    val screenH = g.getScreenHeight.toFloat
    val imgW = background.getImage.getWidth.toFloat
    val imgH = background.getImage.getHeight.toFloat

    val scale = math.min(screenW / imgW, screenH / imgH)
    val drawW = imgW * scale
    val drawH = imgH * scale

    g.drawTransformedPicture(screenW / 2f, screenH / 2f, 0f, drawW / 2f, drawH / 2f, background)
  }

  private def buildAnswerButtons(): Unit = {
    val answers = quiz.currentQuestion.answers
    val totalWidth = 2 * BUTTON_WIDTH + GRID_GAP
    val totalHeight = 2 * BUTTON_HEIGHT + GRID_GAP
    val startX = (Gdx.graphics.getWidth - totalWidth) / 2f
    val startY = (Gdx.graphics.getHeight - totalHeight) / 2f

    buttons = new Array[CustomButton](answers.length)

    for (i <- answers.indices) {
      val col = i % 2
      val row = i / 2

      val button = ButtonFactory.primary(answers(i))
      button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT)
      button.setPosition(
        startX + col * (BUTTON_WIDTH + GRID_GAP),
        startY + (1 - row) * (BUTTON_HEIGHT + GRID_GAP)
      )
      button.onClick(() => onAnswerClicked(i))

      stage.addActor(button)
      buttons(i) = button
    }
  }

  private def onAnswerClicked(index: Int): Unit = {
    if (quiz.getPhase != QuizPhase.ShowingAnswers) return

    val correct = quiz.answer(index)
    if (correct) println("CORRECT") else println("WRONG")

    val correctIndex = quiz.currentQuestion.correctIndex
    for (i <- buttons.indices) {
      buttons(i).setDisabled(i != correctIndex)
      val c = if (i == correctIndex) Color.GREEN else Color.DARK_GRAY
      buttons(i).setColor(c)
    }

    revealTimer = 0f
  }

  private def clearAnswerButtons(): Unit = {
    stage.clear()
    buttons = Array.empty
  }
}
