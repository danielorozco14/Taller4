package com.example.roomwordsample.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.data.entities.Author
import com.example.roomwordsample.data.entities.Book


/**
 * CLASE QUE CONFIGURA EL RECYCLER VIEW
 */

class BookListAdapter internal constructor(context: Context):RecyclerView.Adapter<BookListAdapter.BookViewHolder>(){

    private var booksList= emptyList<Book>()//CACHED COPY OF BOOKS
    private var authorsList=emptyList<Author>()//CACHED COPY OF AUTHORS

    //CLASE QUE OBTIENE EL VIEW QUE SE RECICLARA
    inner class BookViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //Crear todas las variables necesarias para vincular los datos al cardView
        val bookItemViewTitle:TextView=itemView.findViewById(R.id.book_title_View)
        val bookItemViewAuthor:TextView=itemView.findViewById(R.id.book_author_View)

        fun bind(item: Book /**, clickListener: (Book) -> Unit**/) = with(itemView){
            /**Glide.with(itemView.context)
                .load(item.img)
                .placeholder(R.drawable.ic_launcher_background)
                .into(coin_image_cv)**/

            //edit_book_title.setText(item.titulo)

           // this.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.book_cardview, parent, false) //inflater.inflate(R.layout.book_cardview,parent,false)
        Log.d("MEKGO","INFLANDO book_cardview en BookListAdapter")
        return BookViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        Log.d("MEKGO","TAMANIO DE LA LISTA booksList en BookListAdapter: "+booksList.size.toString())
       return booksList.size
    }



    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = booksList[position]
        //val currentAuthor=authorsList[position]
        Log.d("MEKGO","OnBindViewHolder BookListAdapter")
        //val current2:Author= authors[position]
        holder.bookItemViewTitle.text=currentBook.titulo
        //holder.bookItemViewAuthor.text=currentAuthor.name_author

        //holder.bookItemViewTitle.text=current2.name_author
        //holder.bind(booksList[position] /**, clickListener**/)

    }

    internal fun setBooks(books:List<Book>){
        Log.d("MEKGO","setBooks BookListAdapter")
        this.booksList=books
        notifyDataSetChanged()

    }

    internal fun setAuthors(author: List<Author>){
        this.authorsList=author
        notifyDataSetChanged()
    }

}