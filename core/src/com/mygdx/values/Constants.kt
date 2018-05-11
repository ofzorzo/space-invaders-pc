package com.mygdx.values

// The objective of this class is to hold values that will never change
object Constants {
    const val BASE_GAME_WIDTH = 600
    const val BASE_GAME_HEIGHT = 800


    const val BUTTON_WIDTH : Float = 300.0F
    const val BUTTON_HEIGHT : Float = 100.0F
    const val BUTTON_Y_MARGIN : Float = 20.0F

    const val QUIT_BUTTON_X : Float = 150.0F
    const val QUIT_BUTTON_Y : Float = 2 * BUTTON_Y_MARGIN


    const val HELP_BUTTON_X : Float = QUIT_BUTTON_X
    const val HELP_BUTTON_Y : Float = QUIT_BUTTON_Y + BUTTON_HEIGHT + BUTTON_Y_MARGIN

    const val PLAY_BUTTON_X : Float = QUIT_BUTTON_X
    const val PLAY_BUTTON_Y : Float = HELP_BUTTON_Y + BUTTON_HEIGHT + BUTTON_Y_MARGIN

    const val BACK_BUTTON_X : Float = 300.0F
    const val BACK_BUTTON_Y : Float = 100.0F


    // Scenes IDs

    val MAIN_MENU_ID = 0
    val GAME_RUNNING_ID = 1
    val PAUSE_ID = 2
    val GAME_OVER_ID = 3
    val WINNER_ID = 4
    val HELP_ID = 5

    // File paths

    val TEXTURES = "textures/"
    val BACKGROUND = TEXTURES + "background/"
    val BG_IMG_PATH = BACKGROUND + "background.png"

    val HUD = "hud/"
    val PLAY_BUT = HUD + "Play.png"
    val HELP_BUT = HUD + "Help.png"
    val QUIT_BUT = HUD + "Quit.png"
    val BACK_BUT = HUD + "Back.png"

    val MUSIC = "music/"
    val MAIN_MENU_MUSIC = MUSIC + "bgm.mp3"
    val HELP_MUSIC = MUSIC + "helpMusic.mp3"


    val UI = "ui/"
    val FONTS = UI + "fonts/"
    val FNT_FONT = FONTS + "ken_vector.fnt"
    val PNG_FONT = FONTS + "ken_vector.png"


    // Texts

    val HELP_TEXT = "SPACE INVADERS :\n    - Arrows to Move " +
                                    "\n    - Space to Shoot" +
                                    "\n    - P to Pause"

    val HELP_TEXT_X = 50.0F
    val HELP_TEXT_Y = 400.0F


}
