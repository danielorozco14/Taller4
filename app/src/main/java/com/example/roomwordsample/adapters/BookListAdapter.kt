package com.example.roomwordsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.data.entities.Book
import kotlinx.android.synthetic.main.activity_new_word.view.*


/**
 * CLASE QUE CONFIGURA EL RECYCLER VIEW
 */

class BookListAdapter internal constructor(context: Context):RecyclerView.Adapter<BookListAdapter.WordViewHolder>(){

    private val inflater:LayoutInflater= LayoutInflater.from(context)
    private var books= emptyList<Book>()//CACHED COPY OF WORDS

    //CLASE QUE OBTIENE EL VIEW QUE SE RECICLARA
    inner class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val bookItemView:CardView=itemView.findViewById(R.id.book_title_View)

        fun bind(item: Book /**, clickListener: (Book) -> Unit**/) = with(itemView){
            /**Glide.with(itemView.context)
                .load(item.img)
                .placeholder(R.drawable.ic_launcher_background)
                .into(coin_image_cv)**/

            edit_book_title.setText(item.titulo)

           // this.setOnClickListener { clickListener(item) }
        }
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
        holder.bind(books[position] /**, clickListener**/)

    }

    internal fun setWords(books:List<Book>){
        this.books=books
        notifyDataSetChanged()
    }

}