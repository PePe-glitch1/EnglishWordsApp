package com.example.englishwordsappn.data

import androidx.room.*

@Dao
interface WordDao {
    @Insert suspend fun insert(word: Word)
    @Update suspend fun update(word: Word)
    @Delete suspend fun delete(word: Word)

    @Query("SELECT * FROM word_table WHERE learned = 0")
    suspend fun getNotLearned(): List<Word>

    @Query("SELECT * FROM word_table WHERE learned = 1")
    suspend fun getLearned(): List<Word>

    @Query("SELECT COUNT(*) FROM word_table WHERE learned = 1")
    suspend fun learnedCount(): Int

    @Query("SELECT COUNT(*) FROM word_table WHERE learned = 0")
    suspend fun notLearnedCount(): Int

    @Query("SELECT * FROM word_table")
    suspend fun all(): List<Word>

    @Query("UPDATE word_table SET learned = 1 WHERE id = :id")
    suspend fun markLearned(id: Int)

}
