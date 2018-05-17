package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.handlers.InGameHandler
import com.mygdx.values.Constants


class PlayerSpaceship : Actor{

    private var shots = mutableListOf< Pair<Float, Float> >()
    private var moveRight: Boolean = false
    private var moveLeft: Boolean = false
    private var shotSound: Sound

    constructor(game: SpaceInvadersGame) : super(){
        this.setPosition(Constants.SPACESHIP_INITIAL_X_POS, Constants.SPACESHIP_INITIAL_Y_POS)
        this.setSize(Constants.SPACESHIP_WIDTH, Constants.SPACESHIP_HEIGHT)
        this.addListener(InGameHandler(this, game))
        this.shotSound = Gdx.audio.newSound(Gdx.files.internal(Constants.SHOT_SOUND))
    }

    fun createShot(){
        val x: Float = Constants.SPACESHIP_WIDTH/2 + this.getX() - 7.0f
        val y: Float = Constants.SPACESHIP_HEIGHT + 15.0f
        shots.add(Pair(x, y))
        this.shotSound.play()
    }

    fun createShot(x: Float, y: Float){
        shots.add(Pair(x, y))
    }

    fun removeOutterShots(){
        val shotIterator = this.shots.iterator()
        for (shot in shotIterator){
            if(shot.second > Constants.BASE_GAME_HEIGHT){
                shotIterator.remove()
            }
        }
    }

    fun getShots(): MutableList<Pair<Float, Float>> {
        return this.shots
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