package com.example.roomwordsample.data.entities

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Class that works as an Sql Table
 */

@Entity(
    tableName = "Book_table",
    foreignKeys = arrayOf(ForeignKey(entity = Publisher::class,
        parentColumns= arrayOf("publisher_id"),
        childColumns= arrayOf("publisher_id")))
)

//titulo,resumen,isbn,publisher

class Book(
    @ColumnInfo(name = "book_title")
    var titulo:String,

    /**
    @ColumnInfo(name="book_image")
    var imagen:Image,**/

    @ColumnInfo(name = "book_resume")
    var resumen:String,

    @ColumnInfo(name="book_isbn")
    var ISBN:String,

    @ColumnInfo(name="publisher_id")
    var id_Publisher:Int
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="book_id")
    var id_book:Int=1
}

