package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.screens.MainMenu
import com.mygdx.screens.ScreenManager
import com.mygdx.values.Constants
import com.mygdx.values.GameInfo

class SpaceInvadersGame : Game() {
    private lateinit var batch: SpriteBatch
    private lateinit var screens : ScreenManager

    override fun create() {
        batch = SpriteBatch()
        this.screens = ScreenManager(this)
        GameInfo.CURRENT_STATE = Constants.MAIN_MENU_ID
        this.screens.updateScreen()
    }

    override fun dispose() {
        batch.dispose()
    }

    fun getSpriteBatch() : SpriteBatch{
        return this.batch
    }
}
