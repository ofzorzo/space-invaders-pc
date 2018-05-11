package com.mygdx.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const

class GameRunning : SuperScreen {
    private var bg: Texture = Texture(Constants.BG_IMG_PATH)

    constructor(game: SpaceInvadersGame) : super(game)

    override fun render(delta: Float) {

    }

}