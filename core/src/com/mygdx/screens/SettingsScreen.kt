package com.mygdx.screens

import com.badlogic.gdx.Game
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
import com.mygdx.handlers.BackHandler
import com.mygdx.handlers.DoneHandler
import com.mygdx.handlers.RadioHandler
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class SettingsScreen(game: SpaceInvadersGame) : SuperScreen(game) {

    private lateinit var bgTexture : Texture
    private lateinit var titleFont : BitmapFont
    private lateinit var optionsFont : BitmapFont
    private lateinit var doneButton : ImageButton
    //treats radio boxes as buttons
    private lateinit var arrowRadio : ImageButton
    private lateinit var mouseMovRadio : ImageButton
    private lateinit var spaceRadio : ImageButton
    private lateinit var clickRadio : ImageButton
    private lateinit var settingsStage : Stage
    private lateinit var radioOnTextureRegDrawable : TextureRegionDrawable
    private lateinit var radioOffTextureRegDrawable : TextureRegionDrawable


    override fun show() {
        //BACKGROUND IMAGE
        val originalBG = Pixmap(Gdx.files.internal(Constants.BG_IMG_PATH))
        val scaledBG = Pixmap(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT, originalBG.format)
        scaledBG.drawPixmap(originalBG,
                0, 0, originalBG.width, originalBG.height,
                0, 0, scaledBG.width, scaledBG.height)
        this.bgTexture = Texture(scaledBG)
        //FONT
        this.titleFont = BitmapFont(Gdx.files.internal(Constants.FNT_FONT))
        this.titleFont.data.scaleX = 2.0f
        this.titleFont.data.scaleY = 2.0f
        this.optionsFont = BitmapFont(Gdx.files.internal(Constants.FNT_FONT))
        //DONE BUTTON
        val doneTexture = Texture(Constants.DONE_BUT)
        val doneTextureRegDrawable = TextureRegionDrawable(TextureRegion(doneTexture))
        this.doneButton = ImageButton(doneTextureRegDrawable)
        this.doneButton.addListener(DoneHandler(this.game))
        this.doneButton.setPosition(Constants.DONE_BUTTON_X, Constants.DONE_BUTTON_Y)
        this.doneButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)

        //RADIOS

        val radioOnTexture = Texture(Constants.RADIO_ON)
        this.radioOnTextureRegDrawable = TextureRegionDrawable(TextureRegion(radioOnTexture))
        val radioOffTexture = Texture(Constants.RADIO_OFF)
        this.radioOffTextureRegDrawable = TextureRegionDrawable(TextureRegion(radioOffTexture))
        if(GameInfo.MOVEMENT == Constants.ARROWS_ID) {
            this.arrowRadio = ImageButton(radioOnTextureRegDrawable, radioOnTextureRegDrawable)
            this.mouseMovRadio = ImageButton(radioOffTextureRegDrawable, radioOnTextureRegDrawable)
        }
        else{
            this.arrowRadio = ImageButton(radioOffTextureRegDrawable, radioOnTextureRegDrawable)
            this.mouseMovRadio = ImageButton(radioOnTextureRegDrawable, radioOnTextureRegDrawable)
        }
        if(GameInfo.SHOOT == Constants.SPACE_KEY_ID) {
            this.spaceRadio = ImageButton(radioOnTextureRegDrawable, radioOnTextureRegDrawable)
            this.clickRadio = ImageButton(radioOffTextureRegDrawable, radioOnTextureRegDrawable)
        }
        else{
            this.spaceRadio = ImageButton(radioOffTextureRegDrawable, radioOnTextureRegDrawable)
            this.clickRadio = ImageButton(radioOnTextureRegDrawable, radioOnTextureRegDrawable)
        }
        this.arrowRadio.isChecked = true
        this.mouseMovRadio.isChecked = false
        this.spaceRadio.isChecked = true
        this.clickRadio.isChecked = false


        this.arrowRadio.addListener(RadioHandler(this, Constants.ARROWS_ID))
        this.mouseMovRadio.addListener(RadioHandler(this, Constants.MOUSE_MOV_ID))
        this.spaceRadio.addListener(RadioHandler(this, Constants.SPACE_KEY_ID))
        this.clickRadio.addListener(RadioHandler(this, Constants.MOUSE_CLICK_ID))

        this.arrowRadio.setPosition(Constants.ARROW_RADIO_X, Constants.ARROW_MOV_TEXT_Y - 32.0f)
        this.mouseMovRadio.setPosition(Constants.MOUSE_MOV_RADIO_X, Constants.MOUSE_MOV_TEXT_Y- 32.0f)
        this.spaceRadio.setPosition(Constants.SPACE_KEY_RADIO_X, Constants.SPACE_KEY_TEXT_Y- 32.0f)
        this.clickRadio.setPosition(Constants.MOUSE_CLICK_RADIO_X, Constants.MOUSE_CLICK_TEXT_Y- 32.0f)

        this.arrowRadio.setSize(Constants.RADIO_WIDTH, Constants.RADIO_HEIGHT)
        this.mouseMovRadio.setSize(Constants.RADIO_WIDTH, Constants.RADIO_HEIGHT)
        this.spaceRadio.setSize(Constants.RADIO_WIDTH, Constants.RADIO_HEIGHT)
        this.clickRadio.setSize(Constants.RADIO_WIDTH, Constants.RADIO_HEIGHT)

        this.settingsStage = Stage(ScreenViewport())
        this.settingsStage.addActor(this.doneButton)
        this.settingsStage.addActor(this.arrowRadio)
        this.settingsStage.addActor(this.mouseMovRadio)
        this.settingsStage.addActor(this.spaceRadio)
        this.settingsStage.addActor(this.clickRadio)

        Gdx.input.inputProcessor = this.settingsStage

    }



    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        this.titleFont.draw(game.getSpriteBatch(), Constants.SETTINGS_TEXT, Constants.SETTINGS_TEXT_X, Constants.SETTINGS_TEXT_Y)
        this.optionsFont.draw(game.getSpriteBatch(), Constants.MOVEMENT_TEXT, Constants.MOVEMENT_TEXT_X, Constants.MOVEMENT_TEXT_Y)
        this.optionsFont.draw(game.getSpriteBatch(), Constants.ARROW_MOV_TEXT, Constants.ARROW_MOV_TEXT_X, Constants.ARROW_MOV_TEXT_Y)
        this.optionsFont.draw(game.getSpriteBatch(), Constants.MOUSE_MOV_TEXT, Constants.MOUSE_MOV_TEXT_X, Constants.MOUSE_MOV_TEXT_Y)
        this.optionsFont.draw(game.getSpriteBatch(), Constants.SHOOT_TEXT, Constants.SHOOT_TEXT_X, Constants.SHOOT_TEXT_Y)
        this.optionsFont.draw(game.getSpriteBatch(), Constants.SPACE_KEY_TEXT, Constants.SPACE_KEY_TEXT_X, Constants.SPACE_KEY_TEXT_Y)
        this.optionsFont.draw(game.getSpriteBatch(), Constants.MOUSE_CLICK_TEXT, Constants.MOUSE_CLICK_TEXT_X, Constants.MOUSE_CLICK_TEXT_Y)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.settingsStage.act(Gdx.graphics.deltaTime)
        this.settingsStage.draw()

    }

    override fun hide() {
        Gdx.input.inputProcessor = null

    }

    override fun resume() {
        Gdx.input.inputProcessor = this.settingsStage

    }
    //togle an radio button checked
    fun toggleCheck(id:Int){
        when(id){
            Constants.ARROWS_ID ->{
                this.arrowRadio.style.imageUp = this.radioOnTextureRegDrawable
                this.mouseMovRadio.style.imageUp = this.radioOffTextureRegDrawable

            }
            Constants.MOUSE_MOV_ID ->{
                this.arrowRadio.style.imageUp = this.radioOffTextureRegDrawable
                this.mouseMovRadio.style.imageUp = this.radioOnTextureRegDrawable

            }
            Constants.SPACE_KEY_ID->{
                this.spaceRadio.style.imageUp = this.radioOnTextureRegDrawable
                this.clickRadio.style.imageUp = this.radioOffTextureRegDrawable
            }
            Constants.MOUSE_CLICK_ID->{
                this.spaceRadio.style.imageUp = this.radioOffTextureRegDrawable
                this.clickRadio.style.imageUp = this.radioOnTextureRegDrawable
            }
        }

    }
}