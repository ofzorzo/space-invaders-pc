package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants

class GameOver : SuperScreen {

    private var bg: Texture = Texture(Constants.BG_IMG_PATH)

    constructor(game: SpaceInvadersGame, manager : ScreenManager) : super(game, manager)

    override fun render(delta: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}