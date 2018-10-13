package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.GameInfo

class DoneHandler (val game: SpaceInvadersGame) : InputListener(){
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        //TODO: needs to change constants that change the interation
        this.game.getScreenManager().updateScreen(GameInfo.PREVIOUS_STATE)
        return true
    }
}