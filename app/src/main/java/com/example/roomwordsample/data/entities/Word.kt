package com.example.roomwordsample.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that works as an Sql Table
 */

@Entity(tableName = "Book_table")
class Book (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_book")
    val id:Int




)

