package com.example.roomwordsample.data.entities

import android.media.Image
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * Class that works as an Sql Table
 */

@Entity(
    tableName = "Book_table",
    foreignKeys = arrayOf(ForeignKey(entity = Publisher::class,
        parentColumns= arrayOf("publisher_id"),
        childColumns= arrayOf("publisher_id"),onDelete = CASCADE)   /**,
     ForeignKey(entity = Author::class,
        parentColumns = arrayOf("id_author"),childColumns = arrayOf("FK_ID_book"))**/)
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

    /**@ColumnInfo(name = "FK_ID_book")
    var FK_ID_BOOK:Int**/


){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="book_id")
    var id_book:Int = 0
}

