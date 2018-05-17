package com.mygdx.handlers

import com.mygdx.values.Constants
import com.mygdx.values.GameInfo
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class HighScoreHandler(){

    fun initHighScore(){
        GameInfo.HIGHSCORE = this.getHighScore()

    }

    private fun getHighScore() : Int{
        return try {
            val inputStream: InputStream = File(Constants.HIGHSCORE_FILE).inputStream()
            val highscore = inputStream.bufferedReader().use { it.readText() }
            highscore.toInt()
        }catch (e: FileNotFoundException){
            0
        }
    }

    fun checkHighScore(){
        if(GameInfo.HIGHSCORE > this.getHighScore()){
            this.writeHighScore(GameInfo.HIGHSCORE)
        }
    }

    fun writeHighScore(score : Int){
        File(Constants.HIGHSCORE_FILE).writeText(score.toString())

    }
}