package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
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
import com.mygdx.handlers.HelpHandler
import com.mygdx.handlers.PlayHandler
import com.mygdx.handlers.QuitHandler
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class MainMenu(game: SpaceInvadersGame) : SuperScreen(game) {
    private lateinit var bgTexture : Texture
    private lateinit var playButton : ImageButton
    private lateinit var helpButton : ImageButton
    private lateinit var quitButton : ImageButton
    private lateinit var mainMenuStage : Stage
    private lateinit var bgMusic : Music
    private lateinit var font : BitmapFont
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
        this.font.data.scaleX = Constants.MAIN_MENU_X_SCALE
        this.font.data.scaleY = Constants.MAIN_MENU_Y_SCALE

        //PLAY BUTTON
        val playTexture = Texture(Constants.PLAY_BUT)
        val playTextureRegDrawable = TextureRegionDrawable(TextureRegion(playTexture))
        this.playButton = ImageButton(playTextureRegDrawable)
        this.playButton.addListener(PlayHandler(this.game))
        this.playButton.setPosition(Constants.PLAY_BUTTON_X, Constants.PLAY_BUTTON_Y)
        this.playButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //HELP BUTTON
        val helpTexture = Texture(Constants.HELP_BUT)
        val helpTextureRegDrawable = TextureRegionDrawable(TextureRegion(helpTexture))
        this.helpButton = ImageButton(helpTextureRegDrawable)
        this.helpButton.addListener(HelpHandler(this.game))
        this.helpButton.setPosition(Constants.HELP_BUTTON_X, Constants.HELP_BUTTON_Y)
        this.helpButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //QUIT BUTTON
        val quitTexture = Texture(Constants.QUIT_BUT)
        val quitTextureRegDrawable = TextureRegionDrawable(TextureRegion(quitTexture))
        this.quitButton = ImageButton(quitTextureRegDrawable)
        this.quitButton.addListener(QuitHandler(this.game))
        this.quitButton.setPosition(Constants.QUIT_BUTTON_X, Constants.QUIT_BUTTON_Y)
        this.quitButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //STAGE

        this.mainMenuStage = Stage(ScreenViewport())
        // Express that the buttons will act on this screen's stage
        this.mainMenuStage.addActor(this.playButton)
        this.mainMenuStage.addActor(this.helpButton)
        this.mainMenuStage.addActor(this.quitButton)
        Gdx.input.inputProcessor = this.mainMenuStage

        // BACKGROUND MUSIC
        this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MAIN_MENU_MUSIC))
        this.bgMusic.play()
        this.bgMusic.isLooping = true
        this.bgMusic.volume = 0.5f
    }

    //Called each frame, delta is the time difference to previous call of render
    override fun render(delta: Float) {
        //sets the background color - Good practice to LibGDX

        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        this.font.draw(game.getSpriteBatch(), Constants.MAIN_MENU_TEXT_1, Constants.MAIN_MENU_TEXT_1_X, Constants.MAIN_MENU_TEXT_1_Y)
        this.font.draw(game.getSpriteBatch(), Constants.MAIN_MENU_TEXT_2, Constants.MAIN_MENU_TEXT_2_X, Constants.MAIN_MENU_TEXT_2_Y)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.mainMenuStage.act(Gdx.graphics.deltaTime)
        this.mainMenuStage.draw()


    }

    override fun hide() {
        this.bgMusic.stop()
        Gdx.input.inputProcessor = null

    }

    override fun resume() {
        //restart the music
        this.bgMusic.play()
        this.bgMusic.isLooping = true
        //set the proper input handler
        Gdx.input.inputProcessor = this.mainMenuStage

    }

    //Free used memory space when this screen is destroyesd
    override fun dispose() {

        this.bgMusic.dispose()
        this.mainMenuStage.dispose()
        this.bgTexture.dispose()
    }
}


