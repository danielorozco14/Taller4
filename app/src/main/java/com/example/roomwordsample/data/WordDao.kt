package com.example.roomwordsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    @Query("SELECT * FROM WORD_TABLE ORDER BY WORD ASC")
    fun getAllWords():List<Word>

    @Insert
    suspend fun insert(word: Word)

    @Query("DELETE FROM WORD_TABLE")
    fun deleteAll()

}