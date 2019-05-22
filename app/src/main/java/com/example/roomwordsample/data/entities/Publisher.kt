package com.example.roomwordsample.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that works as an Sql Table
 */

@Entity(tableName = "Publisher_table")
class Publisher (
    @ColumnInfo(name = "publisher_name")
    var name_publisher:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "publisher_id")
    var id_publisher:Int=1
}