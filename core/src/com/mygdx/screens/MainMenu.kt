package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
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

class MainMenu : SuperScreen{
    private var bgTexture : Texture
    private var playButton : ImageButton
    private var helpButton : ImageButton
    private var quitButton : ImageButton
    private var mainMenuStage : Stage
    private var bgMusic : Music

    //I preferred to not convert to primary constructor because in my opinion this is clearer
    constructor(game:SpaceInvadersGame) : super(game){

        //BACKGROUND IMAGE
        val originalBG = Pixmap(Gdx.files.internal(Constants.BG_IMG_PATH))
        val scaledBG = Pixmap(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT, originalBG.format)
        scaledBG.drawPixmap(originalBG,
                0, 0, originalBG.width, originalBG.height,
                0, 0, scaledBG.width, scaledBG.height)
        this.bgTexture = Texture(scaledBG)

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
        this.quitButton.addListener(QuitHandler())
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

    }

    //Called each frame, delta is the time difference to previous call of render
    override fun render(delta: Float) {
        //sets the background color - Good practice to LibGDX

        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.mainMenuStage.act(Gdx.graphics.deltaTime)
        this.mainMenuStage.draw()


    }

    //Free used memory space when this screen is destroyesd
    override fun dispose() {

        this.bgMusic.dispose()
        this.mainMenuStage.dispose()
        this.bgTexture.dispose()
    }
}


