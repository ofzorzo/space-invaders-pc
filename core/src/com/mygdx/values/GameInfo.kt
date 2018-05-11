package com.mygdx.values



// The objectives of this class is hold values that may change during the game execution but are widespread through the
// system
object GameInfo {

    //initial dimension values
    var GAME_HEIGHT = Constants.BASE_GAME_HEIGHT
    var GAME_WIDTH = Constants.BASE_GAME_WIDTH

    // scale of the screen size = current_size / base_size
    // 1.0 é o inicial
    var WIDTH_SCALE = 1.0
    var HEIGHT_SCALE = 1.0

    var CURRENT_STATE = Constants.MAIN_MENU_ID


}