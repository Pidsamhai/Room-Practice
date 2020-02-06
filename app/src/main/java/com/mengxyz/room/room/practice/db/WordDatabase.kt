package com.mengxyz.room.room.practice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mengxyz.room.room.practice.db.entity.Word

@Database(entities = [Word::class],version = 1,exportSchema = false)
abstract class WordDatabase:RoomDatabase() {
    abstract fun worldDao():WordDao


//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    val wordDao = database.worldDao()
//
//                    // Delete all content here.
//                    wordDao.deleteAll()
//
//                    // Add sample words.
//                    var word = World("Hello")
//                    wordDao.insert(word)
//                    word = World("World!")
//                    wordDao.insert(word)
//
//                    // TODO: Add your own words!
//                    word = World("TODO!")
//                    wordDao.insert(word)
//                }
//            }
//        }
//    }


    companion object{
        @Volatile
        private var INSTANCE:WordDatabase? = null

        fun getDatabase(context: Context):WordDatabase{
            val tempInstant = INSTANCE
            if(tempInstant != null){
                return tempInstant
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}