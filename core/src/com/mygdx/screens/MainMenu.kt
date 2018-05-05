package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class MainMenu : SuperScreen{
    private var bg:Texture = Texture(Constants.BG_IMG_PATH)

    constructor(game:SpaceInvadersGame, manager : ScreenManager) : super(game, manager)

    //Called each frame, delta is the time
    override fun render(delta: Float) {
        //sets the background color - Good practice to LibGDX
        println("main menu render")
        Gdx.gl.glClearColor(1.0F,0.0F,1.0F,1.0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //Remove everything from the screen


        game.getSpriteBatch().begin() // needs to be called before draw
        game.getSpriteBatch().draw(bg, 0.0F, 0.0F)
        game.getSpriteBatch().end() // needs to be called after drawing
        GameInfo.CURRENT_STATE = Constants.GAME_RUNNING_ID
        this.manager.updateScreen()

    }


}


