package com.example.roomwordsample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwordsample.data.daos.AuthorDao
import com.example.roomwordsample.data.daos.PublisherDao
import com.example.roomwordsample.data.entities.Author
import com.example.roomwordsample.data.entities.Book
import com.example.roomwordsample.data.entities.Publisher
import com.example.roomwordsample.data.entities.Tags
import com.example.roomwordsample.data.room.BookRoomDatabase
import com.example.roomwordsample.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application):AndroidViewModel(application){

    //VARIABLE QUE CONTIENE LA REFERENCIA AL REPOSITORIO
    private val bookRepository:BookRepository


    //VARIABLE QUE CONTIENE LA LISTA DE LIBROS
    val allBooks:LiveData<List<Book>>
    //val allAuthors:LiveData<List<Author>>
    //val allPublishers:LiveData <List<Publisher>>
    //val allTags:LiveData<List<Tags>>


    //OBTIENE UNA REFERENCIA A WordDao desde BookRoomDatabase y construye
    //BookRepository basado en ello

    init {

        val bookDao= BookRoomDatabase.getDatabase(application, viewModelScope).bookDao()
        val authorDao:AuthorDao = BookRoomDatabase.getDatabase(application,viewModelScope).authorDao()
        val publisherDao:PublisherDao = BookRoomDatabase.getDatabase(application,viewModelScope).publisherDao()
        //val tagsDao=BookRoomDatabase.getDatabase(application,viewModelScope).tagsDao()

        bookRepository= BookRepository(bookDao,authorDao,publisherDao)//,tagsDao)

        allBooks= bookRepository.allBooks
       // allAuthors=bookRepository.allAuthors
       // allPublishers=bookRepository.allPublisher
        //allTags=bookRepository.allTags
    }

    //WRAPPER insertBookRepository() llama al metodo insertBookRepository() de Repository
    fun insertBookViewModel(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.insertBookRepository(book)
    }

    /**fun insertAuthorViewModel(author:Author)= viewModelScope.launch (Dispatchers.IO){
        bookRepository.insertAuthorsRepository(author)
    }

    fun insertPublisherViewModel(publisher: Publisher)=viewModelScope.launch (Dispatchers.IO){
        bookRepository.insertPublishersRepository(publisher)
    }**/

    /**fun insertTagsViewModel(tags: Tags)=viewModelScope.launch (Dispatchers.IO){
        bookRepository.insertTagsRepository(tags)
    }**/



}