package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants

class MainMenu : Screen{
    private var game:SpaceInvadersGame
    private var bg:Texture

    constructor(game:SpaceInvadersGame){
        this.game = game
        this.bg = Texture(Constants.BG_IMG_PATH)

    }

    //First method that will be called
    override fun show() {
        TODO("not implemented")

        // do nothing
    }
    //Called each frame, delta is the time
    override fun render(delta: Float) {
        //sets the background color - Good practice to LibGDX

        Gdx.gl.glClearColor(1.0F,0.0F,1.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bg, 0.0F, 0.0F)
        game.getSpriteBatch().end() // needs to be called after drawing

    }
    //scene is paused -> stop rendering but saves the state
    override fun pause() {
        TODO("not implemented")
    }

    //resume from pause
    override fun resume() {
        TODO("not implemented")
    }
    //behaviour when resize
    override fun resize(width: Int, height: Int) {
        TODO("not implemented")
    }

    //application goes to background
    override fun hide() {
        TODO("not implemented")
    }
    //called when it is terminated
    override fun dispose() {
        TODO("not implemented")

    }

}


