package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.values.Constants
import com.mygdx.game.SpaceInvadersGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()

        config.width = Constants.BASE_GAME_WIDTH
        config.height = Constants.BASE_GAME_HEIGHT
        config.forceExit = true // Allows the exit operation to occur
        LwjglApplication(SpaceInvadersGame(), config)
    }
}
