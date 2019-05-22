package com.example.roomwordsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

/**
 * Actividad que maneja el ingreso de palabras nuevas
 */

class NewWordActivity : AppCompatActivity() {

    private lateinit var editBookTitleView: EditText
    private lateinit var editBookAuthorView : EditText
    private lateinit var editBookResumeView:EditText
    private lateinit var editBookPublisherView: EditText
    private lateinit var editBookIsbnView:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editBookTitleView = findViewById(R.id.edit_book_title)
        editBookAuthorView = findViewById(R.id.edit_book_author)
        editBookResumeView=findViewById(R.id.edit_book_resumen)
        editBookIsbnView = findViewById(R.id.edit_book_ISBN)
        editBookPublisherView=findViewById(R.id.edit_book_PUBLISHER)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editBookTitleView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val Book_title = editBookTitleView.text.toString()
                val Book_author:String = editBookAuthorView.text.toString()
                val Book_resume:String = editBookResumeView.text.toString()
                val Book_isbn:String =  editBookIsbnView.text.toString()
                val Book_publisher:String = editBookPublisherView.text.toString()


                replyIntent.putExtra(EXTRA_BOOK_TITLE, Book_title)
                replyIntent.putExtra(EXTRA_BOOK_AUTHOR,Book_author)
                replyIntent.putExtra(EXTRA_BOOK_PUBLISHER,Book_publisher)
                replyIntent.putExtra(EXTRA_BOOK_RESUME,Book_resume)
                replyIntent.putExtra(EXTRA_BOOK_ISBN,Book_isbn)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }


    }
    companion object {
        const val EXTRA_BOOK_TITLE="com.example.android.wordlistsql.REPLY"
        const val EXTRA_BOOK_AUTHOR="com.example.android.wordlistsql.REPLY2"
        const val EXTRA_BOOK_RESUME="com.example.android.wordlistsql.REPLY3"
        const val EXTRA_BOOK_ISBN="com.example.android.wordlistsql.REPLY4"
        const val EXTRA_BOOK_PUBLISHER="com.example.android.worlistsql.REPLY5"
    }
}