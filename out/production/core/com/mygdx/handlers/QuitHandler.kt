package com.mygdx.handlers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class QuitHandler(val game : SpaceInvadersGame) : InputListener() {


    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        when(GameInfo.CURRENT_STATE)
        {
            Constants.PAUSE_ID, Constants.GAME_OVER_ID -> this.game.getScreenManager().updateScreen(Constants.MAIN_MENU_ID)

            Constants.MAIN_MENU_ID -> Gdx.app.exit()

        }
        return true
    }
}