package com.mygdx.screens

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.SpaceInvadersGame
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo


class Pause : SuperScreen {
    private var bg: Texture = Texture(Constants.BG_IMG_PATH)

    constructor(game: SpaceInvadersGame) : super(game)

    override fun render(delta: Float) {
        println("pause render")

    }

}