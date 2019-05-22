package com.example.roomwordsample.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsample.data.entities.Author

/**
 * INTERFAZ PARA TODAS LAS OPERACIONES DE SQLite
 */

@Dao
interface AuthorDao {

    @Query("SELECT * FROM author_table")
    fun getAllAuthors():LiveData<List<Author>>

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(author: Author)

    @Query("DELETE  FROM author_table")
    fun deleteAllAuthors()

}