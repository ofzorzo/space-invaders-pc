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
import com.mygdx.game.EnemyHorde
import com.mygdx.game.PlayerSpaceship
import com.mygdx.handlers.InGameHandler
import com.mygdx.handlers.PlayHandler

class GameRunning(game: SpaceInvadersGame) : SuperScreen(game) {
    private lateinit var bgTexture : Texture
    private lateinit var spaceShipTexture : Texture
    private lateinit var shotsTexture : Texture
    private lateinit var enemyTexture : Texture
    private lateinit var font : BitmapFont
    private lateinit var playStage : Stage
    private lateinit var spaceShip: PlayerSpaceship

    private lateinit var enemyHorde: EnemyHorde
    private var currentScore: Int = 0
    private var initialized : Boolean = false
    private var endGame: Boolean = false
    private var elapsedTimeSinceEnd: Long = 0
    private var endTime: Long = 0


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
        this.shotsTexture = Texture(Pixmap(Gdx.files.internal(Constants.SHOTS_TEXTURE)))
        this.enemyTexture = Texture(Pixmap(Gdx.files.internal(Constants.ENEMY_TEXTURE)))

        //Initialized here to pass game, this is needed to change screens in game
        this.spaceShip = PlayerSpaceship(this.game)
        this.enemyHorde = EnemyHorde(this.spaceShip, this)



        this.playStage = Stage(ScreenViewport())
        this.playStage.addActor(this.spaceShip)
        this.playStage.setKeyboardFocus(this.spaceShip)
        Gdx.input.inputProcessor = this.playStage


        //MUSIC
        this.initialized = true

        this.currentScore = 0
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1.0F,0.0F,0.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen

        //antes de desenhar a nova cena checamos se no último desenho aconteceu alguma colisão ou fim de jogo
        if(this.enemyHorde.checkEndOfGame()) {
            if(this.endGame==true && this.elapsedTimeSinceEnd>1000) {
                this.endGame = false
                this.game.getScreenManager().updateScreen(Constants.GAME_OVER_ID)
            }
            else{
                if(this.endGame==false)
                    this.endTime = System.currentTimeMillis()
                this.endGame = true
                this.elapsedTimeSinceEnd = System.currentTimeMillis() - this.endTime
            }
        }
        this.enemyHorde.checkCollision()

        if(endGame==false) {
            //cria e move horda
            this.enemyHorde.createHordeIfNeeded()
            this.enemyHorde.moveHorde()

            //move nave do jogador
            this.spaceShip.move()

            //destrói tiros que saíram da tela e move os demais
            this.spaceShip.removeOutterShots()
            this.spaceShip.moveShots()
        }

        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bgTexture, 0.0F, 0.0F)
        this.font.draw(game.getSpriteBatch(), this.currentScore.toString(), Constants.CURRENT_SCORE_X, Constants.CURRENT_SCORE_Y)
        this.font.draw(game.getSpriteBatch(), Constants.SCORE_TEXT, Constants.SCORE_TEXT_X, Constants.SCORE_TEXT_Y)
        this.font.draw(game.getSpriteBatch(), GameInfo.HIGHSCORE.toString(), Constants.HIGHSCORE_X, Constants.HIGHSCORE_Y)
        this.font.draw(game.getSpriteBatch(), Constants.HIGH_TEXT, Constants.HIGH_TEXT_X, Constants.HIGH_TEXT_Y)

        //desenha horda
        this.enemyHorde.draw(game, enemyTexture)

        //desenha nave e tiros
        this.spaceShip.draw(game, spaceShipTexture)

        game.getSpriteBatch().end() // needs to be called after drawing
        this.playStage.act(Gdx.graphics.deltaTime)
        this.playStage.draw()
    }

    fun getCurrentScore(): Int {
        return this.currentScore
    }

    fun setCurrentScore(newScore: Int): Int {
        this.currentScore = newScore
        GameInfo.CURRENT_SCORE = newScore // This value is used on Game over screen
        if(GameInfo.CURRENT_SCORE > GameInfo.HIGHSCORE)
            GameInfo.HIGHSCORE = GameInfo.CURRENT_SCORE
        return this.currentScore
    }

    override fun hide() {
        println("running hide()")
        Gdx.input.inputProcessor = null
    }

    override fun resume() {

        println("running resume()")
        Gdx.input.inputProcessor = this.playStage
    }

}