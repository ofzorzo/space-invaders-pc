package com.mygdx.handlers

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.mygdx.game.PlayerSpaceship
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo
import java.lang.Math.sqrt

class InGameHandler(val spaceShip: PlayerSpaceship, val game: SpaceInvadersGame) : InputListener() {

    private var previousX = Constants.SPACESHIP_INITIAL_X_POS
    private var previousY = Constants.SPACESHIP_INITIAL_Y_POS
    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        when{
            (keycode == Constants.RIGHT_ARROW || keycode == 32)&& GameInfo.MOVEMENT == Constants.ARROWS_ID->
                this.spaceShip.publicSetMoveRight(true)
            (keycode == Constants.LEFT_ARROW || keycode == 29) && GameInfo.MOVEMENT == Constants.ARROWS_ID->
                this.spaceShip.publicSetMoveLeft(true)
            keycode == Constants.SPACE_KEY && GameInfo.SHOOT == Constants.SPACE_KEY_ID->
                this.spaceShip.createShot(0.0f, Constants.SHOT_SPEED)
            keycode == Constants.PAUSE_KEY || keycode == Constants.ESC_KEY ->
                this.game.getScreenManager().updateScreen(Constants.PAUSE_ID)
            else -> {println(keycode)}
        }
        return super.keyDown(event, keycode)
    }

    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        var speedX = (x - (Constants.SPACESHIP_WIDTH/2 + spaceShip.x - 7.0f))
        var speedY = (y - Constants.SPACESHIP_HEIGHT)
        var speed = sqrt((speedX*speedX + speedY*speedY).toDouble())
        speedX = Constants.SHOT_SPEED * (speedX / speed.toFloat())
        speedY = Constants.SHOT_SPEED * (speedY / speed.toFloat())
        if(GameInfo.SHOOT == Constants.MOUSE_CLICK_ID)
            this.spaceShip.createShot(speedX, speedY)
        return super.touchDown(event, x, y, pointer, button)
    }


    override fun keyUp(event: InputEvent?, keycode: Int): Boolean {
        if (keycode==22 || keycode == 32)
            this.spaceShip.publicSetMoveRight(false)
        if (keycode==21 || keycode == 29)
            this.spaceShip.publicSetMoveLeft(false)
        return super.keyUp(event, keycode)
    }

}