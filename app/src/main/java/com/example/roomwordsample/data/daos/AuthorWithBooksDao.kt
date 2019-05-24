package com.example.roomwordsample.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.roomwordsample.data.entities.AuthorWithBooks

@Dao
interface AuthorWithBooksDao {

    @Query("SELECT * FROM AUTHOR_TABLE")
    fun getAuthorWithBooks():List<AuthorWithBooks>

    @Query("SELECT * FROM AUTHOR_TABLE WHERE id_author=:authorID")
    fun getBooksListByAuthorId(authorID:Int):List<AuthorWithBooks>

}