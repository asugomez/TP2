package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChoixListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)
        var pseudo: String? = intent.getStringExtra("pseudo")
        this.title="$pseudo lists"

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewChListe)
        val lists: MutableList<List> = mutableListOf()


        repeat(3){
            lists.add(List("list ${it + 1}"))
        }

        val adapter = AdapterList(lists)

        var b=findViewById<Button>(R.id.buttonOkChListe)
        var t=findViewById<EditText>(R.id.editTextListe)
        b.setOnClickListener {
            var newListName = t.text.toString()
            Toast.makeText(this, newListName, Toast.LENGTH_SHORT).show()
            adapter.addData(newListName)
            t.setText("")

        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val change = Intent(this,ShowListActivity::class.java)
        change.putExtra("pseudo", pseudo)

        adapter.setOnItemClickListener(object : AdapterList.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val listName = lists[position].listTextStr
                Toast.makeText(applicationContext, listName, Toast.LENGTH_SHORT).show()
                change.putExtra("list", listName)
                startActivity(change)
            }

        })

        }

    }
