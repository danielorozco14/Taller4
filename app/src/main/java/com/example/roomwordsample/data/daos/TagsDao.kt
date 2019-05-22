package com.example.roomwordsample.data.daos

import android.nfc.Tag
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface TagsDao {

    //ESTO ES LO QUE SE MUESTRA AL USUARIO
    @Query("SELECT * FROM tags_table")
    fun getAllTags(): LiveData<List<Tag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag: Tag)

    @Query("DELETE FROM tags_table")
    fun deleteAllTags()


}