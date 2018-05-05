package com.mygdx.values

// The objective of this class is to hold values that will never change
object Constants {
    val BASE_GAME_WIDTH = 600
    val BASE_GAME_HEIGHT = 800

    // Size of the objects from the game, this should be defined relative to the GAME_WIDTH and GAME_HEIGHT from
    // GameInfo object so that resize occurs naturally, to do this we can proceed from two basic ways:
    // 1 ) define directly percentages of the screen occupied
    // 2 ) define according to BASE_GAME_WIDTH and BASE_GAME_HEIGHT that will be scaled

    // defined as 1
    val PLAYER_WIDTH : Double = 0.1
    //defined as 2
    val PLAYER_HEIGHT : Double = (70.0 / BASE_GAME_HEIGHT)


    // Scenes IDs

    val MAIN_MENU_ID = 0
    val GAME_RUNNING_ID = 1
    val PAUSE_ID = 2
    val GAME_OVER_ID = 3
    val WINNER_ID = 4

    // File paths

    val TEXTURES = "textures/"
    val BACKGROUND = TEXTURES + "background/"
    val BG_IMG_PATH = BACKGROUND + "background.png"
}
