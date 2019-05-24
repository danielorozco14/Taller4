package com.example.roomwordsample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.roomwordsample.R
import com.example.roomwordsample.data.entities.Book
import kotlinx.android.synthetic.main.book_cardview.view.*


class NewBookListAdapter(var books:List<Book>, val clickLister:(Book)->Unit):RecyclerView.Adapter<NewBookListAdapter.ViewHolder> () {


    //INFLA CADA UNO DE LOS ELEMENTOS DE NUESTRA LIST EN LA VISTA
    //INFORMACION QUEMADA QUE VIENE DEL XML cardview_movie
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBookListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_cardview, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(books[position], clickLister)

    fun changeList(movies: List<Book>)
    {
        this.books=movies
        notifyDataSetChanged()
    }

    /**
     * Vista de un elemento de nuestra lista
     */
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item:Book, clickListener: (Book) -> Unit)= with(itemView){
            /**Glide.with(itemView.context)
                .load(item.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(movie_image_cv)**/
            //ID'S DEL XML
            book_title_View.text = item.titulo
            book_resumen.text = item.resumen
           // movie_rate_cv.text = item.imdbRating
            //movie_runtime_cv.text = item.Runtime

            this.setOnClickListener{clickListener(item)}
        }

    }


}