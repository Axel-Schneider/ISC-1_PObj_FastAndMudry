package ch.hevs.fastandmudry
package screens.quiz

import ch.hevs.fastandmudry.core.audio.{AudioManager, MusicTrack}
import ch.hevs.fastandmudry.core.quiz.{Quiz, QuizData, QuizPhase}
import ch.hevs.fastandmudry.core.state.{GameStateMachine, QuizCompleted, Wallet}
import ch.hevs.fastandmudry.screens.AbstractScreen
import ch.hevs.fastandmudry.ui.components.{ButtonFactory, CustomButton}
import ch.hevs.fastandmudry.ui.hud.WalletHUD
import ch.hevs.fastandmudry.utils.Constant.QUIZ
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.{Gdx, Input}

class QuizScreen extends AbstractScreen {
  private var quiz: Quiz = _
  private val background: BitmapImage = new BitmapImage(QUIZ.BACKGROUND_IMAGE)
  private var revealTimer: Float = 0f
  private var buttons: Array[CustomButton] = Array.empty
  private val titleFont: BitmapFont = new BitmapFont()
  titleFont.getData.setScale(1.5f)
  titleFont.setColor(Color.BLACK)

  override def onInit(): Unit = {
    AudioManager.playTrack(MusicTrack.Quiz)
    Gdx.input.setInputProcessor(stage)
    quiz = new Quiz(QuizData.nextSession())
    revealTimer = 0f
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    drawBackground(g)
    g.drawStringCentered(g.getScreenHeight * 0.85f, quiz.currentQuestion.text, titleFont)

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
        if (revealTimer >= QUIZ.REVEAL_DURATION) {
          clearAnswerButtons()
          if (!quiz.doContinueQuestions()) GameStateMachine.handle(QuizCompleted)
        }
      }
    }
    WalletHUD.draw(g)
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
    val totalWidth = 2 * QUIZ.BUTTON_WIDTH + QUIZ.GRID_GAP
    val totalHeight = 2 * QUIZ.BUTTON_HEIGHT + QUIZ.GRID_GAP
    val startX = (Gdx.graphics.getWidth - totalWidth) / 2.2f
    val startY = (Gdx.graphics.getHeight - totalHeight) / 1.5f

    buttons = new Array[CustomButton](answers.length)

    for (i <- answers.indices) {
      val col = i % 2
      val row = i / 2

      val button = ButtonFactory.primary(answers(i))
      button.setSize(QUIZ.BUTTON_WIDTH, QUIZ.BUTTON_HEIGHT)
      button.setPosition(
        startX + col * (QUIZ.BUTTON_WIDTH + QUIZ.GRID_GAP),
        startY + (1 - row) * (QUIZ.BUTTON_HEIGHT + QUIZ.GRID_GAP)
      )
      button.onClick(() => onAnswerClicked(i))

      stage.addActor(button)
      buttons(i) = button
    }
  }

  private def onAnswerClicked(index: Int): Unit = {
    if (quiz.getPhase != QuizPhase.ShowingAnswers) return

    val correct = quiz.answer(index)
    if (correct) Wallet.add(QUIZ.COINS_PER_CORRECT_ANSWER)

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
