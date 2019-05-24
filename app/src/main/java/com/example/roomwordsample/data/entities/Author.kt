package com.example.roomwordsample.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that works as an Sql Table
 */

@Entity(
    tableName = "Author_table"
)
class Author (
    @ColumnInfo(name = "name_author")
    var name_author:String
){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_author")
    var id_author:Int = 0
}