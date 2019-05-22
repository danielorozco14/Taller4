package com.example.roomwordsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.data.entities.Book


/**
 * CLASE QUE CONFIGURA EL RECYCLER VIEW
 */

class BookListAdapter internal constructor(context: Context):RecyclerView.Adapter<BookListAdapter.WordViewHolder>(){

    private val inflater:LayoutInflater= LayoutInflater.from(context)
    private var books= emptyList<Book>()//CACHED COPY OF WORDS

    //CLASE QUE OBTIENE EL VIEW QUE SE RECICLARA
    inner class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val bookItemView:TextView=itemView.findViewById(R.id.book_title_View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView= inflater.inflate(R.layout.book_cardview,parent,false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return books.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = books[position]
        holder.bookItemView.text=current.titulo

    }

    internal fun setWords(books:List<Book>){
        this.books=books
        notifyDataSetChanged()
    }

}