package com.example.roomwordsample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.data.WordRoomDatabase
import com.example.roomwordsample.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application):AndroidViewModel(application){

    //VARIABLE QUE CONTIENE LA REFERENCIA AL REPOSITORIO
    private val repository:WordRepository

    //VARIABLE QUE CONTIENE LA LISTA DE PALABRAS
    val allWords:LiveData<List<Word>>

    //OBTIENE UNA REFERENCIA A WordDao desde WordRoomDatabase y construye
    //WordRepository basado en ello

    init {
        val wordsDao= WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository= WordRepository(wordsDao)
        allWords= repository.allWords
    }

    //WRAPPER insert() llama al metode insert() de Repository
    fun insert(word:Word) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(word)
    }


}