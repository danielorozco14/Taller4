package com.example.roomwordsample.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsample.data.entities.Book

/**
 * INTERFAZ PARA TODAS LAS OPERACIONES DE SQLite
 */

@Dao
interface BookDao {

    //ESTO ES LO QUE SE MUESTRA AL USUARIO
    @Query("SELECT * FROM book_table")
    fun getAllBooks():LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    fun deleteAllBooks()

}