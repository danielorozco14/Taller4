package com.example.roomwordsample.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsample.data.entities.Tags


@Dao
interface TagsDao {

    //ESTO ES LO QUE SE MUESTRA AL USUARIO
    @Query("SELECT * FROM tags_table")
    fun getAllTags(): LiveData<List<Tags>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag: Tags)

    @Query("DELETE FROM tags_table")
    fun deleteAllTags()


}