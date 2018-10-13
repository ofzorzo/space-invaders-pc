package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants

class SettingsHandler(val game: SpaceInvadersGame) : InputListener() {


    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        println("Pressed Help Button")
        this.game.getScreenManager().updateScreen(Constants.SETTINGS_ID)

        return true
    }
}