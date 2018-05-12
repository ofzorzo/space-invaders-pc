package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.PlayerSpaceship
import com.mygdx.handlers.InGameHandler
import com.mygdx.handlers.PlayHandler

class GameRunning : SuperScreen {
    private lateinit var bgTexture : Texture
    private lateinit var spaceShipTexture : Texture
    private lateinit var font : BitmapFont
    private lateinit var playStage : Stage
    private lateinit var bgMusic : Music
    private lateinit var spaceShip: PlayerSpaceship

    private var currentScore: Int
    private var initialized : Boolean = false


    constructor(game: SpaceInvadersGame) : super(game){
        this.currentScore = 0
    }

    override fun show(){

        println("running show()")
        if (!this.initialized)
            this.init()
        else
            this.resume()

    }

    //Executes only once, this cannot be done in the constructor because it may run only to show the screen
    //Separated from show method, because it may not be called when the user continue from pause
    fun init (){
        println("running init")
        val originalBG = Pixmap(Gdx.files.internal(Constants.BG_IMG_PATH))
        val scaledBG = Pixmap(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT, originalBG.format)
        scaledBG.drawPixmap(originalBG,
                0, 0, originalBG.width, originalBG.height,
                0, 0, scaledBG.width, scaledBG.height)
        this.bgTexture = Texture(scaledBG)

        this.font = BitmapFont(Gdx.files.internal(Constants.FNT_FONT))

        this.spaceShipTexture = Texture(Pixmap(Gdx.files.internal(Constants.PLAYER_SPACESHIP)))

        //Initialized here to pass game, this is needed to change screens in game
        this.spaceShip = PlayerSpaceship(this.game)

        this.playStage = Stage(ScreenViewport())
        this.playStage.addActor(this.spaceShip)
        this.playStage.setKeyboardFocus(this.spaceShip)
        Gdx.input.inputProcessor = this.playStage


        //MUSIC
        this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.RUNNING_MUSIC))
        this.bgMusic.play()
        this.bgMusic.isLooping = true
        this.initialized = true
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen

        this.spaceShip.move()

        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        game.getSpriteBatch().draw(spaceShipTexture, this.spaceShip.getX(), this.spaceShip.getY())
        this.font.draw(game.getSpriteBatch(), this.currentScore.toString(), Constants.CURRENT_SCORE_X, Constants.CURRENT_SCORE_Y)
        this.font.draw(game.getSpriteBatch(), Constants.SCORE_TEXT, Constants.SCORE_TEXT_X, Constants.SCORE_TEXT_Y)
        game.getSpriteBatch().end() // needs to be called after drawing
        this.playStage.act(Gdx.graphics.deltaTime)
        this.playStage.draw()
    }

    fun getCurrentScore(): Int {
        return this.currentScore
    }

    fun setCurrentScore(newScore: Int): Int {
        this.currentScore = newScore
        return this.currentScore
    }

    override fun hide() {
        println("running hide()")
        this.bgMusic.stop()
        Gdx.input.inputProcessor = null
    }

    override fun resume() {

        println("running resume()")
        //restart the music
        this.bgMusic.play()
        this.bgMusic.isLooping = true
        //set the proper input handler
        Gdx.input.inputProcessor = this.playStage
    }
}