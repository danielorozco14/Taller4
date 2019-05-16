package com.example.roomwordsample

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.adapters.WordListAdapter
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.viewModel.WordViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //SE INICIALIZA EL RECYCLER VIEW
        val adapter = WordListAdapter(this)
        initRecyclerView(adapter)

        //OBTENIENDO OBJETO ViewModel De ViewModelProvider
        wordViewModel= ViewModelProviders.of(this).get(WordViewModel::class.java)

        //AGREGANDO OBSERVADOR AL LIVEDATA QUE REGRESA LA FUNCION getAllWords()
        wordViewModel.allWords.observe(this, Observer { words ->
            //ACTUALIZANDO LA COPIA DE LAS PALABRAS EN EL ADAPTADOR
            words?.let { adapter.setWords(it) }
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

    fun initRecyclerView( adapter:WordListAdapter){
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)
    }

    /**
     * If the activity returns with RESULT_OK,
     * insert the returned word into the database by calling the insert() method of the WordViewModel.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.let {
                val word= Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
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
