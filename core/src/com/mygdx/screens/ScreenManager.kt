package com.mygdx.screens

import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class ScreenManager {

    private var game : SpaceInvadersGame
    private var game_over_screen : GameOver
    private var game_running_screen : GameRunning
    private var main_menu_screen : MainMenu
    private var pause_screen : Pause
    private var winner_screen : Winner
    private var help_screen : HelpScreen


    constructor(game: SpaceInvadersGame){
        this.game = game
        this.game_running_screen = GameRunning(game)
        this.game_over_screen = GameOver(game)
        this.main_menu_screen = MainMenu(game)
        this.pause_screen = Pause(game)
        this.winner_screen = Winner(game)
        this.help_screen = HelpScreen(game)
    }

    fun updateScreen(){
        this.game.screen =
                when(GameInfo.CURRENT_STATE){
                    Constants.GAME_OVER_ID -> this.game_over_screen
                    Constants.GAME_RUNNING_ID -> this.game_running_screen
                    Constants.PAUSE_ID -> this.pause_screen
                    Constants.MAIN_MENU_ID -> this.main_menu_screen
                    Constants.WINNER_ID -> this.winner_screen
                    Constants.HELP_ID -> this.help_screen
                    else -> this.game.screen
                }
    }


}