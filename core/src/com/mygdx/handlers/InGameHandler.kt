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
            Constants.RIGHT_ARROW -> this.spaceShip.setMoveRight(true)
            Constants.LEFT_ARROW -> this.spaceShip.setMoveLeft(true)
            62 -> this.spaceShip.createShot()
            Constants.PAUSE_KEY, Constants.ESC_KEY ->
                this.game.getScreenManager().updateScreen(Constants.PAUSE_ID)
            45 -> this.game.getScreenManager().updateScreen(Constants.GAME_OVER_ID) // TESTE - vai direto para game over quando aperta espeço
            else -> println("Pressed $keycode") // This is equal to "Pressed " + keycode
        }
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