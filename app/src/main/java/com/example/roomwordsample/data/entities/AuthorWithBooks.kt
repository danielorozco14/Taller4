package com.example.roomwordsample.data.entities

import androidx.room.Embedded
import androidx.room.Relation

class AuthorWithBooks {

    @Embedded
    var author:Author = Author("")

    @Relation(parentColumn = "book_id", entityColumn ="author_id",entity = Book::class)
    var books:List<Book> =listOf()

}