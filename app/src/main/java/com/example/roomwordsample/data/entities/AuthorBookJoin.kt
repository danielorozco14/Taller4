package com.example.roomwordsample.data.entities

import androidx.room.ColumnInfo
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
        childColumns = arrayOf("authorID")),
        ForeignKey(entity = Book::class,
            parentColumns = arrayOf("book_id"),
            childColumns = arrayOf("bookID")))
)
class AuthorBookJoin(

    @ColumnInfo(name = "authorID")
    var authorID:Int,
    @ColumnInfo(name = "bookID")
    var bookID:Int

)