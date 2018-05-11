package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class HelpHandler(val game: SpaceInvadersGame) : InputListener() {


    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        println("Pressed Help Button")
        GameInfo.CURRENT_STATE = Constants.HELP_ID
        this.game.getScreenManager().updateScreen()

        return true
    }
}