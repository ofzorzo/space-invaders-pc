package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class ScreenManager {

    private var game : SpaceInvadersGame
    private var game_over_screen : GameOver
    private var game_running_screen : GameRunning
    private var main_menu_screen : MainMenu
    private var pause_screen : Pause
    private var settings_screen : SettingsScreen
    private var mainMenuMusic: Music
    private var gameMusic: Music
    private var gameOverMusic : Music


    constructor(game: SpaceInvadersGame){
        this.game = game
        this.game_running_screen = GameRunning(game)
        this.game_over_screen = GameOver(game)
        this.main_menu_screen = MainMenu(game)
        this.pause_screen = Pause(game)
        this.settings_screen = SettingsScreen(game)

        //MENU MUSIC
        this.mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MAIN_MENU_MUSIC))
        this.mainMenuMusic.isLooping = true
        this.mainMenuMusic.volume = 0.20f

        //GAME MUSIC
        this.gameMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAME_MUSIC))
        this.gameMusic.isLooping = true
        this.gameMusic.volume = 0.10f

        //GAME OVER
        this.gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAME_OVER_MUSIC))
        this.gameOverMusic.isLooping = true
        this.gameOverMusic.volume = 0.2f
    }

    fun restartAndSetGameScreen(){
        this.game_running_screen.init()
        this.updateScreen(Constants.GAME_RUNNING_ID)
    }

    fun updateScreen(newScreen : Int){
        GameInfo.PREVIOUS_STATE = GameInfo.CURRENT_STATE
        GameInfo.CURRENT_STATE = newScreen
        this.game.screen =
                when(newScreen){
                    Constants.GAME_OVER_ID -> this.game_over_screen
                    Constants.GAME_RUNNING_ID -> this.game_running_screen
                    Constants.PAUSE_ID -> this.pause_screen
                    Constants.MAIN_MENU_ID -> this.main_menu_screen
                    Constants.SETTINGS_ID -> this.settings_screen
                    else -> this.game.screen
                }

        if(GameInfo.CURRENT_STATE==Constants.MAIN_MENU_ID){
            this.gameMusic.stop()
            this.gameOverMusic.stop()
            this.mainMenuMusic.play()
        }
        else if(GameInfo.CURRENT_STATE==Constants.GAME_RUNNING_ID) {
            this.mainMenuMusic.stop()
            this.gameOverMusic.stop()
            this.gameMusic.play()
        }
        else if(GameInfo.CURRENT_STATE == Constants.GAME_OVER_ID){
            this.gameMusic.stop()
            this.gameOverMusic.play()

        }

    }


}