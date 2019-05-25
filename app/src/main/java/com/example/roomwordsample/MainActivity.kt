package com.example.roomwordsample
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.adapters.BookListAdapter
import com.example.roomwordsample.data.entities.Author
import com.example.roomwordsample.data.entities.Book
import com.example.roomwordsample.data.entities.Publisher
import com.example.roomwordsample.viewModel.BookViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var viewManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //SE INICIALIZA EL RECYCLER VIEW
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //OBTENIENDO OBJETO ViewModel De ViewModelProvider
        bookViewModel= ViewModelProviders.of(this).get(BookViewModel::class.java)

        //AGREGANDO OBSERVADOR AL LIVEDATA QUE REGRESA LA FUNCION getAllBooks()
        bookViewModel.allBooks.observe(this, Observer { books ->
            //ACTUALIZANDO LA COPIA DE LAS PALABRAS EN EL ADAPTADOR
            books?.let {
                adapter.setBooks(it)
            }
        })
        /**bookViewModel.allAuthors.observe(this, Observer { authors ->
            authors?.let {
                adapter.setAuthors(it)
            }
        })**/

        //CUANDO EL USUARIO TOCA EL BOTON '+' SE LANZA LA ACTIVIDAD
        fab.setOnClickListener { view ->
            val intent= Intent(this@MainActivity,NewWordActivity::class.java)
            //SE LANZA LA ACTIVIDAD ESPERANDO QUE EL RESULTADO SEA IGUAL RESULT_OK
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * If the activity returns with RESULT_OK,
     * insertBookRepository the returned word into the database by calling the insertBookRepository() method of the BookViewModel.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.let {

                val publisher=Publisher(it.getStringExtra(NewWordActivity.EXTRA_BOOK_PUBLISHER))
               // bookViewModel.insertPublisherViewModel(publisher)

                val author=Author(it.getStringExtra(NewWordActivity.EXTRA_BOOK_AUTHOR))
               // bookViewModel.insertAuthorViewModel(author)
                Log.d("MEKGO","ON ACTIVITY RESULT")

                //TODO 1.POSIBLE ERROR EN PARAMETRO publisher.idpublisher
                val book= Book(it.getStringExtra(NewWordActivity.EXTRA_BOOK_TITLE),it.getStringExtra(NewWordActivity.EXTRA_BOOK_RESUME),
                    it.getStringExtra(NewWordActivity.EXTRA_BOOK_ISBN),1)
                Log.d("MEKGO","ON ACTIVITY RESULT ANTES DE INSERTAR EL BOOK")
                bookViewModel.insertBookViewModel(book)
                Log.d("MEKGO","ON ACTIVITY RESULT DESPUES DE INSERTAR EL BOOK")
            }
        }else{
            Toast.makeText(applicationContext,
                R.string.empty_not_saved,Toast.LENGTH_LONG).show()
        }

    }

    //REQUEST CODE OF UPDATING THE VIEW MODEL VIA LIVE DATA
    companion object{
        const val newWordActivityRequestCode=1
    }

}
