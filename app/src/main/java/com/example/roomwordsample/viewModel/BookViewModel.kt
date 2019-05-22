package com.example.roomwordsample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwordsample.data.entities.Book
import com.example.roomwordsample.data.room.BookRoomDatabase
import com.example.roomwordsample.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application):AndroidViewModel(application){

    //VARIABLE QUE CONTIENE LA REFERENCIA AL REPOSITORIO
    private val repository:BookRepository

    //VARIABLE QUE CONTIENE LA LISTA DE PALABRAS
    val allBooks:LiveData<List<Book>>

    //OBTIENE UNA REFERENCIA A WordDao desde BookRoomDatabase y construye
    //BookRepository basado en ello

    init {
        val bookDao= BookRoomDatabase.getDatabase(application, viewModelScope).bookDao()
        repository= BookRepository(bookDao)
        allBooks= repository.allBooks
    }

    //WRAPPER insert() llama al metodo insert() de Repository
    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(book)
    }


}