package com.mygdx.values



// The objectives of this class is hold values that may change during the game execution but are widespread through the
// system
object GameInfo {

    //initial dimension values
    var GAME_HEIGHT = Constants.BASE_GAME_HEIGHT
    var GAME_WIDTH = Constants.BASE_GAME_WIDTH

    var CURRENT_WAVE:Int = 0
    var CURRENT_SCORE:Int = 0

    var HIGHSCORE : Int = 0

    var CURRENT_STATE = Constants.MAIN_MENU_ID
    var PREVIOUS_STATE = Constants.MAIN_MENU_ID



}