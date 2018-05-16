package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class PlayHandler(val game: SpaceInvadersGame) : InputListener() {


    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        println("Pressed Play Button")
        this.game.getScreenManager().updateScreen(Constants.GAME_RUNNING_ID)

        return true
    }
}