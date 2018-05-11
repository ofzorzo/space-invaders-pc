package com.mygdx.handlers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener

class QuitHandler() : InputListener() {


    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        println("Pressed Quit Button")
        Gdx.app.exit()
        return true
    }
}