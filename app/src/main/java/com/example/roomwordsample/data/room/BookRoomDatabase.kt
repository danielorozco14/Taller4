package com.example.roomwordsample.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomwordsample.data.daos.AuthorDao
import com.example.roomwordsample.data.daos.BookDao
import com.example.roomwordsample.data.daos.PublisherDao
import com.example.roomwordsample.data.daos.TagsDao
import com.example.roomwordsample.data.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Book::class,Author::class,Publisher::class,AuthorBookJoin::class,Tags::class), version = 2)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun authorDao():AuthorDao
    abstract fun publisherDao():PublisherDao
    abstract fun authorBookJoinDao():AuthorBookJoin
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
                    populateDatabase(database.bookDao(),database.publisherDao())
                }
            }
        }
        //FUNCION QUE BORRA LA BASE DE DATOS CADA VEZ QUE SE INICIA LA APP
        //Y LA LLENA CON DOS PALABRAS BASE
        suspend fun populateDatabase(bookDao: BookDao,publisherDao: PublisherDao) {
            publisherDao.deleteAllPublishers()
            bookDao.deleteAllBooks()

            var publisher=Publisher("Santillana")
            publisherDao.insert(publisher)
            publisher=Publisher("Pearson")
            publisherDao.insert(publisher)

            var word = Book("Hello","Best Hello World in The East","2345UAED",1)
            bookDao.insert(word)
            word = Book("World","Best Hello World in The West","3456DASO",2)
            bookDao.insert(word)





        }
    }
}