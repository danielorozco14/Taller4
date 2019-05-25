package com.example.roomwordsample.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsample.data.entities.Publisher

/**
 * INTERFAZ PARA TODAS LAS OPERACIONES DE SQLite
 */

@Dao
interface PublisherDao {

    @Query("SELECT * FROM publisher_table")
    fun getAllPublishers():LiveData<List<Publisher>>

    @Query("SELECT * FROM publisher_table")
    fun getPublishers():List<Publisher>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(publisher: Publisher)

    @Query("DELETE FROM publisher_table")
    fun deleteAllPublishers()

}