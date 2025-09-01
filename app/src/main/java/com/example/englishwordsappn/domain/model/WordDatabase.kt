package com.example.englishwordsappn.domain.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.englishwordsappn.data.Word
import com.example.englishwordsappn.data.WordDao

// WordDatabase.kt
@Database(entities = [Word::class], version = 3, exportSchema = true)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile private var INSTANCE: WordDatabase? = null
        fun getDatabase(context: Context): WordDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                )
                    .createFromAsset("mydb.db")
                    //.fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
    }
}


