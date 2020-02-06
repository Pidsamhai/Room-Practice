package com.mengxyz.room.room.practice.db.repository

import androidx.lifecycle.LiveData
import com.mengxyz.room.room.practice.db.WordDao
import com.mengxyz.room.room.practice.db.entity.Word

class WorldRepository (private val worldDao: WordDao){

    val allWorlds:LiveData<List<Word>> = worldDao.getAll()

    suspend fun insert(word: Word){
        worldDao.insert(word)
    }
}