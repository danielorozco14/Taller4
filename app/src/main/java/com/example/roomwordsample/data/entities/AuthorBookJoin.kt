package com.example.roomwordsample.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Class that works as an Sql Cross Table
 */

@Entity(
    tableName = "AuthorxBook",
    primaryKeys = arrayOf("authorID","bookID"),
    foreignKeys = arrayOf(ForeignKey(entity = Author::class,
        parentColumns = arrayOf("id_author"),
        childColumns = arrayOf("ID_author")),
        ForeignKey(entity = Book::class,
            parentColumns = arrayOf("book_id"),
            childColumns = arrayOf("ID_book")))
)

class AuthorBookJoin(var authorID:Int,var bookID:Int)