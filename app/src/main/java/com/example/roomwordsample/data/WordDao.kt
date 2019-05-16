package com.example.roomwordsample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


/**
 * INTERFAZ PARA TODAS LAS OPERACIONES DE SQLite
 */

@Dao
interface WordDao {

    //ESTO ES LO QUE SE MUESTRA AL USUARIO
    @Query("SELECT * FROM WORD_TABLE ORDER BY WORD ASC")
    fun getAllWords():LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Query("DELETE FROM WORD_TABLE")
    fun deleteAll()

}