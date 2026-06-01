package ch.hevs.fastandmudry
package screens.loading

import core.state.{GameState, GameStateMachine, MapLoaded}
import screens.AbstractScreen

import core.world.World
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.math.Interpolation

class LoadingScreen extends AbstractScreen {
  private var isFinishedLoading = false
  private var tempPixmap: Pixmap = _

  /**
   * Some animation related variables
   */
  private var direction: Int = 1
  private var currentTime: Float = 0
  final private val ANIMATION_LENGTH: Float = 2f // Animation length (in seconds)
  final private val MIN_ANGLE: Float = -20
  final private val MAX_ANGLE: Float = 20

  override def onInit(): Unit = {
    isFinishedLoading = false
    val track = World.INSTANCE.TRACK
    GameStateMachine.getGameState match {
      case GameState.Loading(day) => track.setBiome(day.newBiome)
      case _ =>
    }
    val worker = new Thread(() => {
      tempPixmap = track.buildPixmap()
    })
    worker.setDaemon(true)
    worker.start()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    val t = computePercentage
    val angle: Float = Interpolation.sine.apply(MIN_ANGLE, MAX_ANGLE, t)

    g.drawTransformedPicture(g.getScreenWidth / 2.0f, g.getScreenHeight / 2.0f, angle, 0.7f, LOGO)
    g.drawStringCentered(g.getScreenHeight * 0.9f, "Loading...")

    if (!isFinishedLoading && tempPixmap != null) {
      World.INSTANCE.TRACK.installPixmap(tempPixmap)
      tempPixmap = null
      isFinishedLoading = true
    }

    if (isFinishedLoading) {
      Gdx.app.log("LoadingScreen", "Lancement du jeu !")
      GameStateMachine.handle(MapLoaded)
    }
  }

  /**
   * Compute time percentage for making a looping animation
   *
   * @return the current normalized time
   */
  protected def computePercentage: Float = {
    if (direction == 1) {
      currentTime += Gdx.graphics.getDeltaTime
      if (currentTime > ANIMATION_LENGTH) {
        currentTime = ANIMATION_LENGTH
        direction *= -1
      }
    }
    else {
      currentTime -= Gdx.graphics.getDeltaTime
      if (currentTime < 0) {
        currentTime = 0
        direction *= -1
      }
    }
    currentTime / ANIMATION_LENGTH
  }
}
