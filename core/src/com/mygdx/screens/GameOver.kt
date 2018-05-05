package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants

class GameOver : Screen {
    private var game: SpaceInvadersGame
    private var bg: Texture

    constructor(game: SpaceInvadersGame){
        this.game = game
        this.bg = Texture(Constants.BG_IMG_PATH)

    }
    override fun hide() {
        TODO("not implemented")
    }

    override fun show() {
        TODO("not implemented")
    }

    override fun render(delta: Float) {
        TODO("not implemented")
    }

    override fun pause() {
        TODO("not implemented")
    }

    override fun resume() {
        TODO("not implemented")
    }

    override fun resize(width: Int, height: Int) {
        TODO("not implemented")
    }

    override fun dispose() {
        TODO("not implemented")
    }
}