package com.example.tp1.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1.R
import com.example.tp1.data.DataProvider
import com.example.tp1.data.DataProvider.createList
import com.example.tp1.ui.main.adapter.AdapterList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ChoixListActivity : AppCompatActivity(){


    private val activityScope = CoroutineScope(
        SupervisorJob()
                + Dispatchers.Main
    )

override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_choix_list)
    val pseudo: String? = intent.getStringExtra("pseudo")
    this.title="Listes de $pseudo"
    //token
    val hash: String? = intent.getStringExtra("hash")
    val id_user: String? = intent.getStringExtra("id_user")
    val id_user_int = id_user?.toInt()


    val lists: MutableList<com.example.tp1.data.model.List> = mutableListOf()
    val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewChListe)

    val adapter = AdapterList(lists)

    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    //loadLists()
    activityScope.launch {
        recyclerView.visibility = View.GONE
        try{
            if(hash!=null){
                val lists = DataProvider.getListsFromApi(hash)
                adapter.addData(lists)
            }
        }catch(e: Exception){
            Toast.makeText(this@ChoixListActivity, "${e.message} ", Toast.LENGTH_SHORT).show()
        }
        recyclerView.visibility = View.VISIBLE
    }

    val b=findViewById<Button>(R.id.buttonOkChListe)
    val t=findViewById<EditText>(R.id.editTextListe)

    b.setOnClickListener {
        // to change --> with the user
        activityScope.launch {
            try{
                if(id_user_int!=null && hash!=null){
                    val newListName = t.text.toString()
                    Toast.makeText(this@ChoixListActivity, newListName, Toast.LENGTH_SHORT).show()
                    // add the new list
                    val new_list = createList(id_user_int, newListName, hash)
                    adapter.addData(new_list)
                    t.setText("")
                }
            }catch (e: Exception){
                Toast.makeText(this@ChoixListActivity, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }



    }


    val change = Intent(this, ShowListActivity::class.java)
    change.putExtra("hash", hash)


    adapter.setOnItemClickListener(object : AdapterList.OnItemClickListener {
        override fun onItemClick(position: Int) {
                val listName = lists[position].label
                val id_list = lists[position].id
                val id_user = lists[position].id_user
                Toast.makeText(applicationContext, listName, Toast.LENGTH_SHORT).show()
                change.putExtra("list", listName)
                change.putExtra("id_list", id_list)
                change.putExtra("id_user", id_user)
                startActivity(change)

        }


    })



    }

}
