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
import com.mygdx.handlers.ContinueHandler
import com.mygdx.handlers.SettingsHandler
import com.mygdx.handlers.QuitHandler
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo


class Pause(game: SpaceInvadersGame) : SuperScreen(game) {
    private lateinit var bgTexture : Texture
    private lateinit var font : BitmapFont
    private lateinit var continueButton : ImageButton
    private lateinit var settingsButton : ImageButton
    private lateinit var quitButton : ImageButton

    private lateinit var pauseStage : Stage
   // private lateinit var bgMusic : Music


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
        this.font.data.scaleX = Constants.PAUSE_X_SCALE
        this.font.data.scaleY = Constants.PAUSE_Y_SCALE


        //CONTINUE BUTTON
        val backTexture = Texture(Constants.CONTINUE_BUT)
        val backTextureRegDrawable = TextureRegionDrawable(TextureRegion(backTexture))
        this.continueButton = ImageButton(backTextureRegDrawable)
        this.continueButton.addListener(ContinueHandler(this.game))
        this.continueButton.setPosition(Constants.CONTINUE_BUTTON_X, Constants.CONTINUE_BUTTON_Y)
        this.continueButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)
        //SETTINGS
        val settingsTexture = Texture(Constants.SETTINGS_BUT)
        val settingsTextureRegDrawable = TextureRegionDrawable(TextureRegion(settingsTexture))
        this.settingsButton = ImageButton(settingsTextureRegDrawable)
        this.settingsButton.addListener(SettingsHandler(this.game))
        this.settingsButton.setPosition(Constants.SETTINGS_BUTTON_X, Constants.SETTINGS_BUTTON_Y)
        this.settingsButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)
        //QUIT
        val quitTexture = Texture(Constants.QUIT_BUT)
        val quitTextureRegDrawable = TextureRegionDrawable(TextureRegion(quitTexture))
        this.quitButton = ImageButton(quitTextureRegDrawable)
        this.quitButton.addListener(QuitHandler(this.game))
        this.quitButton.setPosition(Constants.QUIT_BUTTON_X, Constants.QUIT_BUTTON_Y)
        this.quitButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //STAGE
        this.pauseStage = Stage(ScreenViewport())
        this.pauseStage.addActor(this.continueButton)
        this.pauseStage.addActor(this.settingsButton)
        this.pauseStage.addActor(this.quitButton)
        Gdx.input.inputProcessor = this.pauseStage

        //MUSIC
        //this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.PAUSE_MUSIC))
        //this.bgMusic.play()
        //this.bgMusic.isLooping = true
    }



    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        this.font.draw(game.getSpriteBatch(), Constants.PAUSE_TEXT, Constants.PAUSE_TEXT_X, Constants.PAUSE_TEXT_Y)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.pauseStage.act(Gdx.graphics.deltaTime)
        this.pauseStage.draw()

    }

    override fun hide() {
        Gdx.input.inputProcessor = null

    }

    override fun resume() {
        Gdx.input.inputProcessor = this.pauseStage

    }

}