package com.example.roomwordsample
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.adapters.BookListAdapter
import com.example.roomwordsample.data.entities.Book
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
        //val adapter = BookListAdapter(this)
        //initRecyclerView(adapter)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //OBTENIENDO OBJETO ViewModel De ViewModelProvider
        bookViewModel= ViewModelProviders.of(this).get(BookViewModel::class.java)

        //AGREGANDO OBSERVADOR AL LIVEDATA QUE REGRESA LA FUNCION getAllBooks()
        bookViewModel.allBooks.observe(this, Observer { words ->
            //ACTUALIZANDO LA COPIA DE LAS PALABRAS EN EL ADAPTADOR
            words?.let { adapter.setBooks(it) }
        })

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

    fun initRecyclerView( adapter:BookListAdapter){
        viewManager= LinearLayoutManager(this)
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=viewManager
            recyclerview.adapter=adapter
        }
        //recyclerView.adapter=adapter
        //recyclerView.layoutManager=LinearLayoutManager(this)
    }

    /**
     * If the activity returns with RESULT_OK,
     * insert the returned word into the database by calling the insert() method of the BookViewModel.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.let {
                val book= Book(it.getStringExtra(NewWordActivity.EXTRA_BOOK_TITLE),it.getStringExtra(NewWordActivity.EXTRA_BOOK_RESUME),
                    it.getStringExtra(NewWordActivity.EXTRA_BOOK_ISBN),1)
                bookViewModel.insert(book)
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
