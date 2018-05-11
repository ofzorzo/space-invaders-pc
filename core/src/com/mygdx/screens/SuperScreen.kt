package com.mygdx.screens

import com.badlogic.gdx.Screen
import com.mygdx.game.SpaceInvadersGame

/**
 * This class is useful to code reuse among screens related to functions
 * from Screen class
 * */
abstract class SuperScreen : Screen{
    protected var game: SpaceInvadersGame

    constructor(game : SpaceInvadersGame)
    {
        this.game = game
    }

    // Called when the screen goes to background
    override fun hide() {

    }

    // Called when the screen is initialized
    override fun show() {

    }

    // Called to draw screen content : declared abstract because each screen will have different behaviour
    abstract override fun render(delta: Float)

    // Called when the screen is paused
    override fun pause() {

    }
    // Called to resume from pause
    override fun resume() {

    }

    // Called to handle resize
    override fun resize(width: Int, height: Int) {

    }

    // Called when the screen is destroyed
    override fun dispose() {

    }

}