package com.example.roomwordsample.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.roomwordsample.data.entities.Book
import com.example.roomwordsample.data.daos.BookDao


class BookRepository(private val bookDao: BookDao) {

    val allBooks:LiveData<List<Book>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book){
        Log.d("MEKGO","INSERTANDO LIBRO, CLASE REPOSITORY")
        bookDao.insert(book)
    }


}