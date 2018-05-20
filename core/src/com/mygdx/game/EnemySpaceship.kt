package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.values.Constants


class EnemySpaceship : Spaceship{

    override var health: Int = 1
    override var vertices: FloatArray
    override var poly = Polygon()
    override var moveRight: Boolean = true
    override var moveLeft: Boolean = false
    private var hitted: Boolean = false
    private var recoverTime : Int = 0
    private var speed: Float = 2.0f
    private var changeLane: Boolean = false

    constructor(x: Float, y: Float, health: Int) : super(){
        this.vertices = floatArrayOf(x+15.0f, y+0.0f, x+35.0f, y+0.0f, x+49.0f, y+53.0f, x+32.0f, y+3.0f, x+17.0f, y+3.0f, x+0.0f, y+53.0f)
        this.setPosition(x, y)
        this.health = health
        updateSpaceshipPoly()
    }

    override fun draw(game: SpaceInvadersGame, texture: Texture) {
        game.getSpriteBatch().draw(texture, this.getX(), this.getY())
    }

    override fun move() {
        moveRight(speed)
        moveLeft(-speed)
        changeLanes()
    }

    override fun moveLeft(deltaX: Float) {
        if(this.moveLeft==true) {
            this.setPosition(this.getX() + deltaX, this.getY())
            for (j in 0..this.vertices.size - 1)
                if (j % 2 == 0) {
                    this.vertices[j] = this.vertices[j] + deltaX
                    this.updateSpaceshipPoly()
                }
        }
    }

    override fun moveRight(deltaX: Float) {
        if(this.moveRight==true) {
            this.setPosition(this.getX() + deltaX, this.getY())
            for (j in 0..this.vertices.size - 1) {
                if (j % 2 == 0) {
                    this.vertices[j] = this.vertices[j] + deltaX
                    this.updateSpaceshipPoly()
                }
            }
        }
    }

    fun changeLanes(){
        if(this.changeLane==true) {
            this.setPosition(this.getX(), this.getY() - 18.0f)
            for (j in 0..this.vertices.size - 1)
                if (j % 2 != 0) {
                    this.vertices[j] = this.vertices[j] - 18.0f
                    this.updateSpaceshipPoly()
                }
        }
    }

    fun setSpeed(value: Float){
        this.speed = value
    }

    fun getSpeed(): Float{
        return this.speed
    }

    fun getChangeLane(): Boolean{
        return this.changeLane
    }

    fun setChangeLane(value: Boolean){
        this.changeLane = value
    }

    fun takeDamage() {
        this.hitted = true
        this.recoverTime = Constants.RECOVER_TIME
    }

    fun wasHit(): Boolean {
        return this.hitted

    }

    fun decreaseRecoverTime() {
        this.recoverTime -= 1
        if (this.recoverTime == 0)
            this.hitted = false
    }

    fun getRecoverTime() : Int{
        return this.recoverTime
    }
}