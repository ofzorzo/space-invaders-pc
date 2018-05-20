package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.mygdx.values.Constants
import com.mygdx.game.PlayerSpaceship
import com.mygdx.screens.GameRunning
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Polygon

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
            if ( this.moveRight==true && this.enemyHorde[i].getX()+Constants.ENEMY_WIDTH+(2.0f*this.difficulty)>Constants.BASE_GAME_WIDTH-5.0f ) {
                this.changeLane = true
                this.moveLeft = true
                this.moveRight = false
                break
            }
            else if ( this.moveLeft==true && this.enemyHorde[i].getX()-(2.0f*this.difficulty)<5.0f ){
                this.changeLane = true
                this.moveLeft = false
                this.moveRight = true
                break
            }
        }
        for(i in 0..this.enemyHorde.size-1){
            enemyHorde[i].setChangeLane(this.changeLane)
            enemyHorde[i].setSpeed(2.0f*this.difficulty)
            enemyHorde[i].publicSetMoveRight(this.moveRight)
            enemyHorde[i].publicSetMoveLeft(this.moveLeft)
            this.enemyHorde[i].move()
        }
    }

    //collision of a shot with an enemy
    private fun collision(enemy: EnemySpaceship, shootx: Float, shooty: Float) : Boolean{

        val shootPoly = Polygon()
        val verticesForShoot = floatArrayOf(
                shootx, shooty,
                shootx + Constants.SHOT_WIDTH, shooty,
                shootx, shooty + Constants.SHOT_HEIGHT,
                shootx + Constants.SHOT_WIDTH, shooty + Constants.SHOT_HEIGHT)
        shootPoly.vertices = verticesForShoot
        return (Intersector.overlapConvexPolygons(enemy.getSpaceshipPoly(), shootPoly))

    }

    //collision of an enemy with the player
    private fun collision(enemy : EnemySpaceship, player : PlayerSpaceship) : Boolean{
        return (Intersector.overlapConvexPolygons(enemy.getSpaceshipPoly(), player.getSpaceshipPoly()))
    }

    fun checkCollision(){
        val shotIterator = this.playerSpaceship.getShots().iterator()
        var collision : Boolean
        //var index: Int = 0
        //if(playerSpaceship.getShots().size>0)
        //    println("\n\niniciou")
        for (shot in shotIterator){
            val enemyIterator = this.enemyHorde.iterator()
            collision = false
            //println(indexss.toString())
            while (enemyIterator.hasNext() && !collision){
                //println(index.toString() + "zrz")
                val enemy = enemyIterator.next()
                //if(index == 1){
                //  println(index.toString() + "  shot_x:" + shot.first + "   shot_y:" + shot.second)
                //  println("enemy_x:" + enemy.getX() + "   enemy_y:" + enemy.getY())}
                if(collision(enemy, shot.first, shot.second))
                {
                    //println("\n\ncolisao")
                    collision = true
                    enemy.publicSetHealth(enemy.publicGetHealth()-1)
                    //println("nro anterior de enemies: " + this.enemyHorde.size)
                    if(enemy.publicGetHealth()==0) {
                        enemyIterator.remove()
                        gameRunning.setCurrentScore(gameRunning.getCurrentScore() + 50)
                    }
                    else
                        enemy.takeDamage()
                    //println("nro posterior de enemies: " + this.enemyHorde.size)

                    this.explosionSound.play()
                    //println("nro anterior de tiros: " + this.playerSpaceship.getShots().size)
                    shotIterator.remove()
                    //println("nro posterior de tiros: " + this.playerSpaceship.getShots().size)
                    //println("fim colisao")
                }
            }
            //index = index + 1
        }
        val enemyIterator = this.enemyHorde.iterator()
        for (enemy in enemyIterator){
            if(collision(enemy, this.playerSpaceship))
            {
                this.playerSpaceship.publicSetHealth(this.playerSpaceship.publicGetHealth()-1)
            }
        }
    }

    fun checkEndOfGame(): Boolean {
        val enemyIterator = this.enemyHorde.iterator()
        for (enemy in enemyIterator) {
            if (enemy.getY() <= 0)
                return true
            if(this.playerSpaceship.publicGetHealth()<1)
            {
                return true
            }

        }
        return false
    }

    fun draw(game: SpaceInvadersGame, texture: Texture){
        for(i in 0..this.enemyHorde.size-1){
            val enemy = this.enemyHorde[i]
            if (!enemy.wasHit() || enemy.getRecoverTime() % 10 < 5) {
                this.enemyHorde[i].draw(game, texture)
            }
            enemy.decreaseRecoverTime()
        }
    }

}