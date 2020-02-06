package com.mengxyz.room.room.practice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mengxyz.room.room.practice.db.WordDatabase
import com.mengxyz.room.room.practice.db.entity.Word
import com.mengxyz.room.room.practice.db.repository.WorldRepository
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
    private val repository:WorldRepository
    val allWorls:LiveData<List<Word>>
    init {
        val worldDao = WordDatabase.getDatabase(application).worldDao()
        repository = WorldRepository(worldDao)
        allWorls = repository.allWorlds
    }

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}