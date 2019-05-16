package com.example.roomwordsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that works as an SqliteDatabaseManager
 */

@Entity(tableName = "word_table")
class Word (@PrimaryKey @ColumnInfo (name="word")val word:String)

