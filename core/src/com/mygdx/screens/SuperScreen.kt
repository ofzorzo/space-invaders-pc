package com.mygdx.screens

import com.badlogic.gdx.Screen
import com.mygdx.game.SpaceInvadersGame

/**
 * This class is useful to code reuse among screens related to functions
 * from Screen class
 * */
abstract class SuperScreen : Screen{
    protected var game: SpaceInvadersGame
    protected var manager : ScreenManager

    constructor(game : SpaceInvadersGame, manager : ScreenManager)
    {
        this.game = game
        this.manager = manager
    }

    // Called when the screen goes to background
    override fun hide() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Called when the screen is initialized
    override fun show() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Called to draw screen content : declared abstract because each screen will have different behaviour
    abstract override fun render(delta: Float)

    // Called when the screen is paused
    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    // Called to resume from pause
    override fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Called to handle resize
    override fun resize(width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Called when the screen is destroyed
    override fun dispose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}