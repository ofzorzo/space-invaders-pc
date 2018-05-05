package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.screens.MainMenu
import com.mygdx.screens.ScreenManager

class SpaceInvadersGame : Game() {
    private lateinit var batch: SpriteBatch
    private lateinit var screens : ScreenManager

    override fun create() {
        batch = SpriteBatch()
        setScreen(MainMenu(this))
        this.screens = ScreenManager(this)
    }

    override fun dispose() {
        batch.dispose()
    }

    fun getSpriteBatch() : SpriteBatch{
        return this.batch
    }
}
