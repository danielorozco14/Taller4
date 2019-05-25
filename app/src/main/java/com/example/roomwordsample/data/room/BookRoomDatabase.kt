package com.example.roomwordsample.data.room

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomwordsample.R
import com.example.roomwordsample.data.daos.*
import com.example.roomwordsample.data.entities.*
import kotlinx.android.synthetic.main.activity_new_word.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Book::class,Author::class,Publisher::class,AuthorBookJoin::class,Tags::class), version = 4)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun authorDao():AuthorDao
    abstract fun publisherDao():PublisherDao
    abstract fun authorBookJoinDao():AuthorBookDao
    abstract fun tagsDao(): TagsDao

    companion object {

        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        //getDatabase() recibe el contexto de la app y el scope para el callback de onOpen()

        fun getDatabase(context: Context, scope: CoroutineScope): BookRoomDatabase {
            //PATRON SINGLETON
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "Books_database"
                ).addCallback(WordDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                return instance

            }
        }
    }

    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.bookDao(),database.publisherDao(),database.authorDao())//,database.authorBookJoinDao())
                }
            }
        }
        //FUNCION QUE BORRA LA BASE DE DATOS CADA VEZ QUE SE INICIA LA APP
        //Y LA LLENA CON DOS PALABRAS BASE
        suspend fun populateDatabase(bookDao: BookDao,publisherDao: PublisherDao,authorDao: AuthorDao){//,authorBookDao: AuthorBookDao) {
            Log.d("MEKGO","BORRANDO DATOS DE LAS TABLAS")
            authorDao.deleteAllAuthors()
            bookDao.deleteAllBooks()
            publisherDao.deleteAllPublishers()


            Log.d("MEKGO","DATOS BORRADOS,COMENZANDO A LLENAR LA BASE")

            var publisher=Publisher("Santillana")
            publisherDao.insert(publisher)
            Log.d("MEKGO","PUBLISHER 1 AGREGADO")
            publisher=Publisher("Pearson")
            publisherDao.insert(publisher)
            Log.d("MEKGO","PUBLISHER 2 AGREGADO")

            var book = Book("Hola","Best Hello World in The East","2345UAED",1)
            bookDao.insert(book)
            Log.d("MEKGO","LIBRO 1 AGREGADO")
            book = Book("Mundo","Best Hello World in The West","3456DASO",1)
            bookDao.insert(book)
            Log.d("MEKGO","LIBRO 2 AGREGADO")

            var author=Author("Sor Juana Ines De La Cruz")
            authorDao.insert(author)
            Log.d("MEKGO","AUTOR 1 AGREGADO")
            author=Author("Michael Jackson")
            authorDao.insert(author)
            Log.d("MEKGO","AUTOR 2 AGREGADO")


           // var authorBookJoin=AuthorBookJoin(1,1)
            //authorBookDao.insertBookRepository(authorBookJoin)
            /**authorBookJoin=AuthorBookJoin(3,2)
            authorBookDao.insertBookRepository(authorBookJoin)**/


        }
    }
}