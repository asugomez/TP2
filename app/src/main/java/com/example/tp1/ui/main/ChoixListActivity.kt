package com.example.tp1.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1.R
import com.example.tp1.data.DataProvider
import com.example.tp1.data.model.List
import com.example.tp1.ui.main.adapter.AdapterList
import kotlinx.coroutines.*

class ChoixListActivity : AppCompatActivity(){

    private val activityScope = CoroutineScope(
        SupervisorJob()
                + Dispatchers.Main
                + CoroutineExceptionHandler { _, throwable ->
            Log.e("ChoixActivity", "CoroutineExceptionHandler : ${throwable.message}")
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)
        val pseudo: String? = intent.getStringExtra("pseudo")
        this.title = "Listes de $pseudo"

        val lists = mutableListOf<List>()
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewChListe)

        val adapter = AdapterList(lists)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        loadList(recyclerView, adapter)

        /*val thread = Thread(Runnable {
            val lists_user = DataProvider.getListsFromApi()
            Log.d("MAINACTIIVYT", "posts: $lists_user")
            runOnUiThread(){
                adapter.showData(lists_user)
            }
        })
        thread.start()

         */




        val b=findViewById<Button>(R.id.buttonOkChListe)
        val t=findViewById<EditText>(R.id.editTextListe)

        b.setOnClickListener {
            // to change --> with the user
            val newListName = t.text.toString()
            Toast.makeText(this, newListName, Toast.LENGTH_SHORT).show()
            //adapter.addData(newListName)
            t.setText("")

        }



        val change = Intent(this, ShowListActivity::class.java)
        change.putExtra("pseudo", pseudo)

        /*
        adapter.setOnItemClickListener(object : AdapterList.OnItemClickListener {
            override fun onItemClick(position: Int) {

                //À changer:
                val listName = lists[position].listTextStr
                Toast.makeText(applicationContext, listName, Toast.LENGTH_SHORT).show()
                change.putExtra("list", listName)
                startActivity(change)

            }

        })



         */
        }


    private fun loadList(
        list: RecyclerView,
        adapter: AdapterList
    ) {
        activityScope.launch {
            list.visibility = View.GONE
            try{
                val lists = DataProvider.getListsFromApi()
                adapter.showData(lists)
            }catch(e: Exception){
                Toast.makeText(this@ChoixListActivity, "${e.message} ", Toast.LENGTH_SHORT).show()
            }
            list.visibility = View.VISIBLE

        }

    }

    override fun onDestroy() {
        activityScope.cancel()
        super.onDestroy()
    }


}
