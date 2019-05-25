package com.example.roomwordsample.data.daos

import androidx.core.provider.FontsContractCompat
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsample.data.entities.Author
import com.example.roomwordsample.data.entities.AuthorBookJoin
import com.example.roomwordsample.data.entities.Book


@Dao
interface AuthorBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(authorBookJoin: AuthorBookJoin);

    /**
     * @Query1("SELECT * FROM Author_table INNER JOIN AuthorxBook ON
    Author_table.id=user_repo_join.userId WHERE
    user_repo_join.repoId=:repoId")
    List<User> getUsersForRepository(final int repoId);

    @Query1("SELECT * FROM repo INNER JOIN user_repo_join ON
    repo.id=user_repo_join.repoId WHERE
    user_repo_join.userId=:userId")
    List<Repo> getRepositoriesForUsers(final int userId);
     */
    @Query("SELECT * FROM Author_table INNER JOIN AuthorxBook ON Author_table.id_author=AuthorxBook.authorID WHERE AuthorxBook.bookID=:authorID")
    fun getAuthorsOfBooks(authorID:Int):LiveData<List<Author>>

    @Query("SELECT * FROM BOOK_TABLE INNER JOIN AUTHORXBOOK ON Book_Table.book_id=AuthorxBook.bookID WHERE AuthorxBook.bookID=:bookID")
    fun getBooksOfAuthors(bookID:Int):List<Book>


}