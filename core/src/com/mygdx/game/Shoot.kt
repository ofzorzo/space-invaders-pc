package com.mygdx.game

import java.lang.Math.atan
import kotlin.math.atan

class Shoot(var x: Float, var y: Float, var dx: Float, var dy: Float) {
    var ang = -90.0f

    init {
        if(dx == 0.0f){
            ang = 0.0f
        }
        else {
            ang = -90 + (180/Math.PI * atan(dy / dx)).toFloat()
            if (dx < 0) {
                ang +=180
            }
        }
    }
    fun getPos():Pair<Float, Float>{
        return Pair(x, y)
    }
    fun move(){
        this.x = this.x + this.dx
        this.y = this.y + this.dy
    }
    fun getAngle(): Float{
        return ang
    }
}