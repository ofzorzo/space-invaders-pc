package com.mygdx.values

// The objective of this class is to hold values that will never change
object Constants {
    const val BASE_GAME_WIDTH = 600
    const val BASE_GAME_HEIGHT = 800

    const val SPACESHIP_WIDTH = 98.0f
    const val SPACESHIP_HEIGHT = 75.0f

    const val SPACESHIP_INITIAL_X_POS = (BASE_GAME_WIDTH/2)-(SPACESHIP_WIDTH/2)
    const val SPACESHIP_INITIAL_Y_POS = 10.0f

    const val HORDE_X = 75.0f
    const val HORDE_Y = 810.0f
    const val SPACE_BETWEEN_LINES_OF_ENEMIES = 5.0f
    const val SPACE_BETWEEN_COLUMNS_OF_ENEMIES = 50.0f

    const val ENEMY_WIDTH = 50.0f
    const val ENEMY_HEIGHT = 73.0f

    const val MOVEMENT_SPEED = 5.0f // número de pixels com que a nave é movida a cada novo frame
    const val SHOT_SPEED = 50.0f

    const val SHOT_HEIGHT = 27
    const val SHOT_WIDTH = 8

    const val BUTTON_WIDTH : Float = 224.0F
    const val BUTTON_HEIGHT : Float = 75.0F
    private const val BUTTON_Y_MARGIN : Float = 40.0F

    const val QUIT_BUTTON_X : Float = 188.0F
    const val QUIT_BUTTON_Y : Float = 2 * BUTTON_Y_MARGIN

    const val SETTINGS_BUTTON_X : Float = QUIT_BUTTON_X
    const val SETTINGS_BUTTON_Y : Float = QUIT_BUTTON_Y + BUTTON_HEIGHT + BUTTON_Y_MARGIN

    const val DONE_BUTTON_X : Float = QUIT_BUTTON_X
    const val DONE_BUTTON_Y : Float = 100.0F

    const val PLAY_BUTTON_X : Float = QUIT_BUTTON_X
    const val PLAY_BUTTON_Y : Float = SETTINGS_BUTTON_Y + BUTTON_HEIGHT + BUTTON_Y_MARGIN

    const val BACK_BUTTON_X : Float = 300.0F
    const val BACK_BUTTON_Y : Float = 100.0F

    const val CONTINUE_BUTTON_X: Float = PLAY_BUTTON_X
    const val CONTINUE_BUTTON_Y: Float = PLAY_BUTTON_Y

    const val GO_QUIT_BUTTON_X : Float = 40.0F
    const val GO_QUIT_BUTTON_Y : Float = 4* BUTTON_Y_MARGIN

    const val AGAIN_BUTTON_X : Float =  600.0F - BUTTON_WIDTH - GO_QUIT_BUTTON_X
    const val AGAIN_BUTTON_Y : Float = GO_QUIT_BUTTON_Y

    // Scenes IDs
    const val RADIO_WIDTH = 40.0f
    const val RADIO_HEIGHT = 40.0f

    const val MAIN_MENU_ID = 0
    const val GAME_RUNNING_ID = 1
    const val PAUSE_ID = 2
    const val GAME_OVER_ID = 3
    const val SETTINGS_ID = 5

    // File paths

    private const val TEXTURES = "textures/"
    private const val BACKGROUND = TEXTURES + "background/"
    const val BG_IMG_PATH = BACKGROUND + "background.png"
    const val PLAYER_SPACESHIP = TEXTURES + "player2.png"
    const val SHOTS_TEXTURE = TEXTURES + "laser2.png"
    const val ENEMY_TEXTURE = TEXTURES + "enemySpaceship.png"

    private const val HUD = "hud/"
    const val PLAY_BUT = HUD + "Play.png"
    const val SETTINGS_BUT = HUD + "Settings.png"
    const val DONE_BUT = HUD + "Done.png"
    const val QUIT_BUT = HUD + "Quit.png"
    const val BACK_BUT = HUD + "Back.png"
    const val CONTINUE_BUT = HUD + "Continue.png"
    const val AGAIN_BUT = HUD + "Again.png"
    const val RADIO_ON = HUD + "radioOn.png"
    const val RADIO_OFF = HUD + "radioOff.png"
    const val CURSOR = HUD + "aim.png"
    const val NORMAL_CURSOR = HUD + "normalCursor.png"
    private const val MUSIC = "music/"
    const val MAIN_MENU_MUSIC = MUSIC + "imperialMarch.mp3"
    const val SETTINGS_MUSIC = MUSIC + "imperialMarch.mp3"
    const val GAME_MUSIC = MUSIC + "starWarsMainTheme.mp3"
    const val PAUSE_MUSIC = MUSIC + "starWarsMainTheme.mp3"
    const val GAME_OVER_MUSIC = MUSIC + "evilMortyTheme.mp3"

    private const val SOUNDS = "sounds/"
    const val SHOT_SOUND = SOUNDS + "shotSound.mp3"
    const val EXPLOSION_SOUND = SOUNDS + "explosionSound.mp3"

    private const val UI = "ui/"
    private const val FONTS = UI + "fonts/"
    const val FNT_FONT = FONTS + "ken_vector.fnt"
    val PNG_FONT = FONTS + "ken_vector.png"


    const val HIGHSCORE_FILE = "highscore.txt"


    // Texts

    const val SETTINGS_TEXT = "SETTINGS"

    const val SETTINGS_TEXT_X = 150.0F
    const val SETTINGS_TEXT_Y = 650.0F

    const val MOVEMENT_TEXT = "Movement: "
    const val MOVEMENT_TEXT_X = SETTINGS_TEXT_X + 100.0f
    const val MOVEMENT_TEXT_Y = SETTINGS_TEXT_Y - 100.0f
    const val ARROW_MOV_TEXT = "Arrows"
    const val ARROW_MOV_TEXT_X = MOVEMENT_TEXT_X + 10.0f
    const val ARROW_MOV_TEXT_Y = MOVEMENT_TEXT_Y - 50.0f
    const val ARROW_RADIO_X = ARROW_MOV_TEXT_X - 50.0f
    const val MOUSE_MOV_TEXT = "Mouse movement"
    const val MOUSE_MOV_TEXT_X = ARROW_MOV_TEXT_X
    const val MOUSE_MOV_TEXT_Y = ARROW_MOV_TEXT_Y - 30.0f
    const val MOUSE_MOV_RADIO_X = ARROW_RADIO_X
    const val SHOOT_TEXT = "Shoot: "
    const val SHOOT_TEXT_X = MOVEMENT_TEXT_X
    const val SHOOT_TEXT_Y = MOUSE_MOV_TEXT_Y - 60.0f
    const val SPACE_KEY_TEXT = "Space key"
    const val SPACE_KEY_TEXT_X = ARROW_MOV_TEXT_X
    const val SPACE_KEY_TEXT_Y = SHOOT_TEXT_Y - 50.0f
    const val SPACE_KEY_RADIO_X = ARROW_RADIO_X
    const val MOUSE_CLICK_TEXT = "Mouse click"
    const val MOUSE_CLICK_TEXT_X = ARROW_MOV_TEXT_X
    const val MOUSE_CLICK_TEXT_Y = SPACE_KEY_TEXT_Y - 30.0f
    const val MOUSE_CLICK_RADIO_X = ARROW_RADIO_X
    const val SCORE_TEXT = "SCORE: "
    const val SCORE_TEXT_X = 15.0F
    const val SCORE_TEXT_Y = 790.0f

    //RADIOS IDS
    const val ARROWS_ID = 0
    const val MOUSE_MOV_ID = 1
    const val SPACE_KEY_ID = 2
    const val MOUSE_CLICK_ID = 3

    const val CURRENT_SCORE_X = 143.0f
    const val CURRENT_SCORE_Y = SCORE_TEXT_Y

    const val HIGH_TEXT = "HIGH: "
    const val HIGH_TEXT_X = 400.0F
    const val HIGH_TEXT_Y = SCORE_TEXT_Y
    const val HIGHSCORE_X = HIGH_TEXT_X + 90.0F
    const val HIGHSCORE_Y = SCORE_TEXT_Y



    const val PAUSE_TEXT = "PAUSE"
    const val PAUSE_TEXT_X = 200.0F
    const val PAUSE_TEXT_Y = 500.0F
    const val PAUSE_X_SCALE = 2.0F
    const val PAUSE_Y_SCALE = 2.0F

    const val MAIN_MENU_TEXT_1 = "SPACE"
    const val MAIN_MENU_TEXT_2 = "INVADERS"
    const val MAIN_MENU_TEXT_1_X = 150.0F
    const val MAIN_MENU_TEXT_1_Y = 650.0F
    const val MAIN_MENU_TEXT_2_X = 50.0F
    const val MAIN_MENU_TEXT_2_Y = MAIN_MENU_TEXT_1_Y - 100.0F

    const val MAIN_MENU_X_SCALE = 3.0F
    const val MAIN_MENU_Y_SCALE = 3.0F

    const val GAME_OVER_X_SCALE = 5.0F
    const val GAME_OVER_Y_SCALE = 5.0F

    const val GAME_OVER_TEXT_1 = "GAME"
    const val GAME_OVER_TEXT_2 = "OVER"
    const val GAME_OVER_TEXT_1_X = 80.0F
    const val GAME_OVER_TEXT_1_Y = 650.0F
    const val GAME_OVER_TEXT_2_X = 90.0F
    const val GAME_OVER_TEXT_2_Y = GAME_OVER_TEXT_1_Y - 150.0F

    const val GO_SCORE_TEXT_X = 100.0F
    const val GO_SCORE_TEXT_Y = GAME_OVER_TEXT_2_Y - 175.0F
    const val GO_SCORE_X_SCALE = 1.5F
    const val GO_SCORE_Y_SCALE = 1.5F
    const val GO_CURRENT_SCORE_X = GO_SCORE_TEXT_X + 150.0F
    const val GO_CURRENT_SCORE_Y = GO_SCORE_TEXT_Y
    const val GO_HIGH_TEXT_X = GO_CURRENT_SCORE_X + 100.0F
    const val GO_HIGH_TEXT_Y = GO_SCORE_TEXT_Y
    const val GO_HIGH_SCORE_X = GO_HIGH_TEXT_X + 90.0F
    const val GO_HIGH_SCORE_Y = GO_SCORE_TEXT_Y

    // Char values

    const val RIGHT_ARROW = 22
    const val LEFT_ARROW = 21
    const val PAUSE_KEY = 44
    const val ESC_KEY = 131
    const val SPACE_KEY = 62


    val CREDITS = "Por Felipe Zorzo Pereira e William Wilbert Vargas - 2018"
    val RECOVER_TIME: Int = 20




}
