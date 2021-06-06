package com.example.tp1.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1.R
import com.example.tp1.ui.main.adapter.AdapterList
import com.example.tp1.ui.main.adapter.List

class ChoixListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)
        var pseudo: String? = intent.getStringExtra("pseudo")
        this.title="$pseudo lists"

        val lists: MutableList<List> = mutableListOf()
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewChListe)

        val adapter = AdapterList(lists)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        repeat(3){
            lists.add(List("list ${it + 1}"))
        }


        val b=findViewById<Button>(R.id.buttonOkChListe)
        val t=findViewById<EditText>(R.id.editTextListe)

        b.setOnClickListener {
            // to change --> with the user
            val newListName = t.text.toString()
            Toast.makeText(this, newListName, Toast.LENGTH_SHORT).show()
            adapter.addData(newListName)
            t.setText("")

        }


        val change = Intent(this, ShowListActivity::class.java)
        change.putExtra("pseudo", pseudo)


        adapter.setOnItemClickListener(object : AdapterList.OnItemClickListener {
            override fun onItemClick(position: Int) {

                //À changer:
                val listName = lists[position].listTextStr
                Toast.makeText(applicationContext, listName, Toast.LENGTH_SHORT).show()
                change.putExtra("list", listName)
                startActivity(change)

            }

        })

        }

    }
