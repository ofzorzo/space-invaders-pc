package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.screens.SettingsScreen
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class RadioHandler(val settings: SettingsScreen, private val id: Int): InputListener(){

    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        this.settings.toggleCheck(this.id)
        when(this.id){
            Constants.ARROWS_ID -> {
                GameInfo.MOVEMENT = Constants.ARROWS_ID
            }
            Constants.MOUSE_MOV_ID -> {
                GameInfo.MOVEMENT = Constants.MOUSE_MOV_ID
            }
            Constants.SPACE_KEY_ID ->{
                GameInfo.SHOOT = Constants.SPACE_KEY_ID
            }
            Constants.MOUSE_CLICK_ID -> {
                GameInfo.SHOOT = Constants.MOUSE_CLICK_ID
            }

        }
        return true
    }
}