package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.mygdx.values.Constants
import com.mygdx.game.PlayerSpaceship
import com.mygdx.screens.GameRunning
import com.badlogic.gdx.math.Intersector

class EnemyHorde(val playerSpaceship: PlayerSpaceship, val gameRunning: GameRunning) {

    private var enemyHorde = mutableListOf< EnemySpaceship >()
    private var difficulty: Int = 0
    private var moveLeft: Boolean = false
    private var moveRight: Boolean = true
    private var changeLane: Boolean = false
    private var explosionSound: Sound = Gdx.audio.newSound(Gdx.files.internal(Constants.EXPLOSION_SOUND))

    fun createHordeIfNeeded(){
        var x: Float
        var y: Float
        var health: Int
        if(this.isEmpty()) {
            if (this.difficulty<3)
                this.difficulty += 1 // every new horde increments the difficulty until it reaches 3
            for (line in 0..(1+this.difficulty)) { // creates new horde
                for (column in 0..4) {
                    x = Constants.HORDE_X + (column * Constants.ENEMY_WIDTH) + (column * Constants.SPACE_BETWEEN_COLUMNS_OF_ENEMIES)
                    y = Constants.HORDE_Y + (line * Constants.ENEMY_HEIGHT)
                    health = this.difficulty
                    this.enemyHorde.add(EnemySpaceship(x, y, health))
                }
            }
        }
    }

    fun isEmpty(): Boolean{
        if (enemyHorde.size == 0)
            return true
        else return false
    }

    fun getEnemyHorde(): MutableList< EnemySpaceship >{
        return this.enemyHorde
    }

    fun moveHorde(){
        this.changeLane = false
        for(i in 0..this.enemyHorde.size-1){ // tests if any of the remaining enemy ships hits one of the side walls; if it does, we should change lanes and the movement direction
            if ( this.moveRight==true && this.enemyHorde[i].getX()+Constants.ENEMY_WIDTH+5.0f>Constants.BASE_GAME_WIDTH-5.0f ) {
                this.changeLane = true
                this.moveLeft = true
                this.moveRight = false
                break
            }
            else if ( this.moveLeft==true && this.enemyHorde[i].getX()-5.0f<5.0f ){
                this.changeLane = true
                this.moveLeft = false
                this.moveRight = true
                break
            }
        }
        //moves every ship to the left
        if(this.moveLeft==true){
            for(i in 0..this.enemyHorde.size-1){
                this.enemyHorde[i].setPosition(this.enemyHorde[i].getX()-(2.0f*this.difficulty), this.enemyHorde[i].getY())
                val verticesForEnemy = this.enemyHorde[i].getVerticesForEnemy()
                for(j in 0..verticesForEnemy.size-1)
                    if(j%2 == 0) {
                        verticesForEnemy[j] = verticesForEnemy[j] - (2.0f * this.difficulty)
                        this.enemyHorde[i].setVerticesForEnemy(verticesForEnemy)
                        this.enemyHorde[i].updateEnemyPoly()
                    }
            }
        }
        //moves every ship to the right
        else
        {
            for(i in 0..this.enemyHorde.size-1){
                this.enemyHorde[i].setPosition(this.enemyHorde[i].getX()+(2.0f*this.difficulty), this.enemyHorde[i].getY())
                val verticesForEnemy = this.enemyHorde[i].getVerticesForEnemy()
                for(j in 0..verticesForEnemy.size-1)
                    if(j%2 == 0){
                        verticesForEnemy[j] = verticesForEnemy[j] + (2.0f*this.difficulty)
                        this.enemyHorde[i].setVerticesForEnemy(verticesForEnemy)
                        this.enemyHorde[i].updateEnemyPoly()
                    }
            }
        }
        //if applicable, changes lanes
        if(this.changeLane==true){
            for(i in 0..this.enemyHorde.size-1){
                this.enemyHorde[i].setPosition(this.enemyHorde[i].getX(), this.enemyHorde[i].getY()-18.0f)
                val verticesForEnemy = this.enemyHorde[i].getVerticesForEnemy()
                for(j in 0..verticesForEnemy.size-1)
                    if(j%2 != 0){
                        verticesForEnemy[j] = verticesForEnemy[j] - 18.0f
                        this.enemyHorde[i].setVerticesForEnemy(verticesForEnemy)
                        this.enemyHorde[i].updateEnemyPoly()
                    }
            }
        }
    }

    fun checkCollision(){
        val shotIterator = this.playerSpaceship.getShots().iterator()
        val enemyIterator = this.enemyHorde.iterator()
        for (shot in shotIterator){
            for (enemy in enemyIterator){
                if (shot.first >= enemy.getX() && shot.first <= (enemy.getX()+Constants.ENEMY_WIDTH)){
                    if(shot.second >= enemy.getY() && shot.second <= (enemy.getY()+Constants.ENEMY_HEIGHT)){
                        enemy.setHealth(enemy.getHealth()-1)
                        if(enemy.getHealth()==0) {
                            enemyIterator.remove()
                            gameRunning.setCurrentScore(gameRunning.getCurrentScore() + 50)
                        }
                        shotIterator.remove()
                        this.explosionSound.play()
                    }
                }
            }
        }
    }

    fun checkEndOfGame(): Boolean {
        val enemyIterator = this.enemyHorde.iterator()
        for (enemy in enemyIterator) {
            if (enemy.getY() <= 0)
                return true
            if(Intersector.overlapConvexPolygons(enemy.getEnemyPoly(), this.playerSpaceship.getPlayerPoly()))
            {
                return true
            }

        }
        return false
    }

}