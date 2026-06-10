package ch.hevs.fastandmudry.ui.hud

import ch.hevs.fastandmudry.core.state.Wallet
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align

  object WalletHUD {
    val mudryCoinImg = new Texture("data/images/MudryCoin.png")
    val font = new BitmapFont()
    font.getData.setScale(2f)
    val margin = 30
    val pictureWidth = 200
    val pictureHeight = 100
    val between = 10
    def draw(g: GdxGraphics): Unit = {
      g.drawString(g.getScreenWidth - pictureWidth - margin - between, g.getScreenHeight - pictureHeight/2 - margin + font.getXHeight/2 , Wallet.coins.toString, font, Align.bottomRight)
      g.draw(mudryCoinImg, g.getScreenWidth - pictureWidth - margin, g.getScreenHeight - pictureHeight - margin, pictureWidth, pictureHeight)
    }
  }
