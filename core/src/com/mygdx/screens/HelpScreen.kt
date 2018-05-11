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
import com.mygdx.handlers.BackHandler
import com.mygdx.handlers.QuitHandler
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class HelpScreen(game: SpaceInvadersGame) : SuperScreen(game) {

    private lateinit var bgTexture : Texture
    private lateinit var font : BitmapFont
    private lateinit var backButton : ImageButton
    private lateinit var helpStage : Stage
    private lateinit var bgMusic : Music


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
        //BACK BUTTON
        val backTexture = Texture(Constants.BACK_BUT)
        val backTextureRegDrawable = TextureRegionDrawable(TextureRegion(backTexture))
        this.backButton = ImageButton(backTextureRegDrawable)
        this.backButton.addListener(BackHandler(this.game))
        this.backButton.setPosition(Constants.BACK_BUTTON_X, Constants.BACK_BUTTON_Y)
        this.backButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        this.helpStage = Stage(ScreenViewport())
        this.helpStage.addActor(this.backButton)
        Gdx.input.inputProcessor = this.helpStage

        //MUSIC
        this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.HELP_MUSIC))
        this.bgMusic.play()
        this.bgMusic.isLooping = true
    }



    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        this.font.draw(game.getSpriteBatch(), Constants.HELP_TEXT, Constants.HELP_TEXT_X, Constants.HELP_TEXT_Y)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.helpStage.act(Gdx.graphics.deltaTime)
        this.helpStage.draw()

    }

    override fun hide() {
        this.bgMusic.stop()
        Gdx.input.inputProcessor = null

    }

    override fun resume() {
        this.bgMusic.play()
        Gdx.input.inputProcessor = this.helpStage

    }
}