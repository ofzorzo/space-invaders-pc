package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class Spaceship : Actor(){

    protected abstract var health: Int
    protected abstract var vertices: FloatArray
    protected abstract var poly: Polygon
    protected abstract var moveRight: Boolean
    protected abstract var moveLeft: Boolean

    abstract fun moveRight(deltaX: Float)

    abstract fun moveLeft(deltaX: Float)

    abstract fun move()

    abstract fun draw(game: SpaceInvadersGame, texture: Texture)

    fun publicGetHealth():Int{
        return this.health
    }

    fun publicSetHealth(value: Int){
        this.health = value
    }

    fun publicGetMoveRight():Boolean{
        return this.moveRight
    }

    fun publicSetMoveRight(value: Boolean){
        this.moveRight = value
    }

    fun publicGetMoveLeft(): Boolean{
        return this.moveLeft
    }

    fun publicSetMoveLeft(value: Boolean){
        this.moveLeft = value
    }

    fun getSpaceshipPoly(): Polygon {
        return this.poly
    }

    fun updateSpaceshipPoly(){
        this.poly.setVertices(this.vertices)
    }

}