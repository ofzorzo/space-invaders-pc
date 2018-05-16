package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

//Back button on help screen and winner screen both go back to main menu
class BackHandler(val game:SpaceInvadersGame) : InputListener(){
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

        this.game.getScreenManager().updateScreen(GameInfo.PREVIOUS_STATE)

        return true
    }
}