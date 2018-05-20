package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.PlayerSpaceship
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class InGameHandler(val spaceShip: PlayerSpaceship, val game: SpaceInvadersGame) : InputListener() {

    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        when (keycode) {
            Constants.RIGHT_ARROW -> this.spaceShip.publicSetMoveRight(true)
            Constants.LEFT_ARROW -> this.spaceShip.publicSetMoveLeft(true)
            Constants.SPACE_KEY -> this.spaceShip.createShot()
            Constants.PAUSE_KEY, Constants.ESC_KEY ->
                this.game.getScreenManager().updateScreen(Constants.PAUSE_ID)
            else -> println("Pressed $keycode") // This is equal to "Pressed " + keycode
        }
        return super.keyDown(event, keycode)
    }

    override fun keyUp(event: InputEvent?, keycode: Int): Boolean {
        if (keycode==22)
            this.spaceShip.publicSetMoveRight(false)
        if (keycode==21)
            this.spaceShip.publicSetMoveLeft(false)
        return super.keyUp(event, keycode)
    }

}