package com.mygdx.game

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.scenes.scene2d.Actor
import com.mygdx.values.Constants


class EnemySpaceship : Actor{

    private var health: Int = 1
    private var verticesForEnemy: FloatArray
    private var enemyPoly = Polygon()
    private var hitted: Boolean = false
    private var recoverTime : Int = 0

    constructor(x: Float, y: Float, health: Int) : super(){
        this.setPosition(x, y)
        this.health = health
        verticesForEnemy = floatArrayOf(x+15.0f, y+0.0f, x+35.0f, y+0.0f, x+49.0f, y+53.0f, x+32.0f, y+3.0f, x+17.0f, y+3.0f, x+0.0f, y+53.0f)//x, y, x, y+Constants.ENEMY_HEIGHT, x+Constants.ENEMY_WIDTH, y+Constants.ENEMY_HEIGHT, x+Constants.ENEMY_WIDTH, y)
        enemyPoly.setVertices(verticesForEnemy)
    }

    fun getVerticesForEnemy(): FloatArray{
        return this.verticesForEnemy
    }

    fun setVerticesForEnemy(newVertices: FloatArray){
        this.verticesForEnemy = newVertices
    }

    fun getEnemyPoly(): Polygon{
        return this.enemyPoly
    }

    fun updateEnemyPoly(){
        this.enemyPoly.setVertices(this.verticesForEnemy)
    }

    fun getHealth(): Int{
        return this.health
    }

    fun setHealth(newHealth: Int){
        this.health = newHealth
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