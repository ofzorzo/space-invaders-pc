package com.mygdx.game

import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.handlers.InGameHandler
import com.mygdx.values.Constants


class PlayerSpaceship : Actor{

    private var shots = mutableListOf< Pair<Int, Int> >()
    private var moveRight: Boolean = false
    private var moveLeft: Boolean = false

    constructor(game: SpaceInvadersGame) : super(){
        this.setPosition(Constants.SPACESHIP_INITIAL_X_POS, Constants.SPACESHIP_INITIAL_Y_POS)
        this.setSize(Constants.SPACESHIP_WIDTH, Constants.SPACESHIP_HEIGHT)
        this.addListener(InGameHandler(this, game))
    }

    fun move(){
        moveRight()
        moveLeft()
    }

    fun moveRight(){
        if( (this.moveRight == true) && (this.getX()+Constants.SPACESHIP_WIDTH+Constants.MOVEMENT_SPEED <= Constants.BASE_GAME_WIDTH) )
            this.setPosition(this.getX()+Constants.MOVEMENT_SPEED, this.getY())
    }

    fun moveLeft(){
        if( (this.moveLeft == true) && (this.getX()-Constants.MOVEMENT_SPEED >= 0) )
            this.setPosition(this.getX()-Constants.MOVEMENT_SPEED, this.getY())
    }

    fun setMoveRight(newBool: Boolean){
        this.moveRight = newBool
    }

    fun getMoveRight(): Boolean{
        return this.moveRight
    }

    fun setMoveLeft(newBool: Boolean){
        this.moveLeft = newBool
    }

    fun getMoveLeft(): Boolean{
        return this.moveLeft
    }

}