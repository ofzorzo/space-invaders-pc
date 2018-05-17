package com.mygdx.game

import com.badlogic.gdx.scenes.scene2d.Actor


class EnemySpaceship : Actor{

    private var health: Int = 1

    constructor(x: Float, y: Float, health: Int) : super(){
        this.setPosition(x, y)
        this.health = health
    }

    fun getHealth(): Int{
        return this.health
    }

    fun setHealth(newHealth: Int){
        this.health = newHealth
    }

}