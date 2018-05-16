package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.screens.ScreenManager
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class SpaceInvadersGame : Game() {
    private lateinit var batch: SpriteBatch
    private lateinit var screens : ScreenManager


    override fun create() {
        batch = SpriteBatch()
        this.screens = ScreenManager(this)
        this.screens.updateScreen(Constants.MAIN_MENU_ID)

    }

    override fun dispose() {
        batch.dispose()
    }

    fun getSpriteBatch() : SpriteBatch{
        return this.batch
    }
    fun getScreenManager() : ScreenManager{
        return this.screens
    }
}
