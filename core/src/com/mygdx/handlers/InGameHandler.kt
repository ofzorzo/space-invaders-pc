package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.PlayerSpaceship

class InGameHandler(val spaceShip: PlayerSpaceship) : InputListener() {

    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        if (keycode==22)
            this.spaceShip.setMoveRight(true)
        if (keycode==21)
            this.spaceShip.setMoveLeft(true)

        return super.keyDown(event, keycode)
    }

    override fun keyUp(event: InputEvent?, keycode: Int): Boolean {
        if (keycode==22)
            this.spaceShip.setMoveRight(false)
        if (keycode==21)
            this.spaceShip.setMoveLeft(false)
        return super.keyUp(event, keycode)
    }

}