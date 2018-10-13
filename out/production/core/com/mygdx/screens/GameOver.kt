package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.handlers.AgainHandler
import com.mygdx.handlers.HighScoreHandler
import com.mygdx.handlers.QuitHandler
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class GameOver(game: SpaceInvadersGame) : SuperScreen(game) {

    private lateinit var bgTexture : Texture
    private lateinit var againButton : ImageButton
    private lateinit var quitButton : ImageButton
    private lateinit var gameOverStage : Stage
    private lateinit var font : BitmapFont
    private lateinit var creditsFont : BitmapFont
    private lateinit var scoreFont : BitmapFont


    override fun show() {
        //BACKGROUND IMAGE
        val originalBG = Pixmap(Gdx.files.internal(Constants.BG_IMG_PATH))
        val scaledBG = Pixmap(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT, originalBG.format)
        scaledBG.drawPixmap(originalBG,
                0, 0, originalBG.width, originalBG.height,
                0, 0, scaledBG.width, scaledBG.height)
        this.bgTexture = Texture(scaledBG)
        //FONT
        this.font = BitmapFont(Gdx.files.internal(Constants.FNT_FONT))
        this.font.data.scaleX = Constants.GAME_OVER_X_SCALE
        this.font.data.scaleY = Constants.GAME_OVER_Y_SCALE
        this.creditsFont = BitmapFont(Gdx.files.internal(Constants.FNT_FONT))
        this.creditsFont.data.scaleX = 0.5f
        this.creditsFont.data.scaleY = 0.5f
        this.scoreFont = BitmapFont(Gdx.files.internal(Constants.FNT_FONT))

        //AGAIN BUTTON
        val againTexture = Texture(Constants.AGAIN_BUT)
        val againTextureRegDrawable = TextureRegionDrawable(TextureRegion(againTexture))
        this.againButton = ImageButton(againTextureRegDrawable)
        this.againButton.addListener(AgainHandler(this.game))
        this.againButton.setPosition(Constants.AGAIN_BUTTON_X, Constants.AGAIN_BUTTON_Y)
        this.againButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //QUIT BUTTON
        val quitTexture = Texture(Constants.QUIT_BUT)
        val quitTextureRegDrawable = TextureRegionDrawable(TextureRegion(quitTexture))
        this.quitButton = ImageButton(quitTextureRegDrawable)
        this.quitButton.addListener(QuitHandler(this.game))
        this.quitButton.setPosition(Constants.GO_QUIT_BUTTON_X, Constants.GO_QUIT_BUTTON_Y)
        this.quitButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //STAGE

        this.gameOverStage = Stage(ScreenViewport())
        // Express that the buttons will act on this screen's stage
        this.gameOverStage.addActor(this.againButton)
        this.gameOverStage.addActor(this.quitButton)
        Gdx.input.inputProcessor = this.gameOverStage

        HighScoreHandler().checkHighScore()
    }

    //Called each frame, delta is the time difference to previous call of render
    override fun render(delta: Float) {
        //sets the background color - Good practice to LibGDX

        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        this.font.draw(game.getSpriteBatch(), Constants.GAME_OVER_TEXT_1, Constants.GAME_OVER_TEXT_1_X, Constants.GAME_OVER_TEXT_1_Y)
        this.font.draw(game.getSpriteBatch(), Constants.GAME_OVER_TEXT_2, Constants.GAME_OVER_TEXT_2_X, Constants.GAME_OVER_TEXT_2_Y)

        this.scoreFont.draw(game.getSpriteBatch(), Constants.SCORE_TEXT, Constants.GO_SCORE_TEXT_X, Constants.GO_SCORE_TEXT_Y)
        this.scoreFont.draw(game.getSpriteBatch(), GameInfo.CURRENT_SCORE.toString(), Constants.GO_CURRENT_SCORE_X, Constants.GO_CURRENT_SCORE_Y)

        this.scoreFont.draw(game.getSpriteBatch(), Constants.HIGH_TEXT, Constants.GO_HIGH_TEXT_X, Constants.GO_HIGH_TEXT_Y)
        this.scoreFont.draw(game.getSpriteBatch(), GameInfo.HIGHSCORE.toString(), Constants.GO_HIGH_SCORE_X, Constants.GO_HIGH_SCORE_Y)

        this.creditsFont.draw(game.getSpriteBatch(), Constants.CREDITS, 25.0f,15.0f)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.gameOverStage.act(Gdx.graphics.deltaTime)
        this.gameOverStage.draw()


    }

    override fun hide() {
        Gdx.input.inputProcessor = null

    }

    override fun resume() {
        //set the proper input handler
        Gdx.input.inputProcessor = this.gameOverStage

    }

    //Free used memory space when this screen is destroyesd
    override fun dispose() {

        this.gameOverStage.dispose()
        this.bgTexture.dispose()
    }

}