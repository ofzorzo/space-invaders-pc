package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Polygon
import com.mygdx.handlers.InGameHandler
import com.mygdx.values.Constants
import java.lang.Math.atan


class PlayerSpaceship : Spaceship{

    override var health: Int = 1
    override var vertices = floatArrayOf(0.0f+Constants.SPACESHIP_INITIAL_X_POS, 9.0f+Constants.SPACESHIP_INITIAL_Y_POS, 48.0f+Constants.SPACESHIP_INITIAL_X_POS, 75.0f+Constants.SPACESHIP_INITIAL_Y_POS, 98.0f+Constants.SPACESHIP_INITIAL_X_POS, 9.0f+Constants.SPACESHIP_INITIAL_Y_POS)
    override var poly = Polygon()
    override var moveRight: Boolean = false
    override var moveLeft: Boolean = false
    private var shots = mutableListOf<Shoot> ()
    private var shotSound: Sound


    constructor(game: SpaceInvadersGame) : super(){
        this.setPosition(Constants.SPACESHIP_INITIAL_X_POS, Constants.SPACESHIP_INITIAL_Y_POS)
        this.setSize(Constants.SPACESHIP_WIDTH, Constants.SPACESHIP_HEIGHT)
        this.addListener(InGameHandler(this, game))
        this.shotSound = Gdx.audio.newSound(Gdx.files.internal(Constants.SHOT_SOUND))
        this.updateSpaceshipPoly()
    }

    override fun moveRight(deltaX: Float){
        if(this.moveRight && (this.x + deltaX + Constants.SPACESHIP_WIDTH <= Constants.BASE_GAME_WIDTH)) {
            this.setPosition(this.x + deltaX, this.y)
            for (i in 0 until this.vertices.size) {
                if(i%2 == 0) {
                    this.vertices[i] = this.vertices[i] + deltaX
                    this.updateSpaceshipPoly()
                }
            }

        }
    }

    override fun moveLeft(deltaX: Float){
        if(this.moveLeft && (this.x +deltaX >= 0)) {
            this.setPosition(this.x +deltaX, this.getY())
            for (i in 0 until this.vertices.size) {
                if(i%2 == 0) {
                    this.vertices[i] = this.vertices[i]+deltaX
                    this.updateSpaceshipPoly()
                }
            }
        }
    }

    override fun move(){
        moveRight(Constants.MOVEMENT_SPEED)
        moveLeft(-Constants.MOVEMENT_SPEED)
    }

    override fun draw(game: SpaceInvadersGame, texture: Texture) {
        game.getSpriteBatch().draw(texture, this.x, this.y)
        drawShots(game)
    }

    fun createShot(speedX: Float, speedY: Float){
        val x: Float = Constants.SPACESHIP_WIDTH/2 + this.x - 7.0f
        val y: Float = Constants.SPACESHIP_HEIGHT
        shots.add(Shoot(x, y, speedX, speedY))
        this.shotSound.play()
    }

    fun moveShots(){
        this.shots.forEach {
            it.move()
        }
    }

    fun getShots(): MutableList<Shoot> {
        return this.shots
    }

    private fun drawShots(game: SpaceInvadersGame){

        shots.forEach {


            game.getSpriteBatch().draw(TextureRegion(Texture(Pixmap(Gdx.files.internal(Constants.SHOTS_TEXTURE)))),
                    it.x, it.y, (Constants.SHOT_WIDTH/2).toFloat(), (Constants.SHOT_HEIGHT/2).toFloat(),
                    Constants.SHOT_WIDTH.toFloat(), Constants.SHOT_HEIGHT.toFloat(), 1.0f, 1.0f, it.getAngle())
        }

    }

    fun removeOutterShots(){
        val shotIterator = this.shots.iterator()
        for (shot in shotIterator){
            if(shot.y > Constants.BASE_GAME_HEIGHT){
                shotIterator.remove()
            }
        }
    }

    fun mouseMove(x: Float) {
        val speed = x - this.x
        this.setPosition(x.toFloat(), this.y)
        for (i in 0 until this.vertices.size) {
            if(i%2 == 0) {
                this.vertices[i] = this.vertices[i]+speed
                this.updateSpaceshipPoly()
            }
        }
    }
}