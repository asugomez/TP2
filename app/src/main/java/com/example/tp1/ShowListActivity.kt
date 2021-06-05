package com.example.tp1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ShowListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        var list_name: String? = intent.getStringExtra("list")
        this.title = "Items of $list_name"

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewChObject)
        val items: MutableList<Item> = mutableListOf()

        repeat(3){
            items.add(Item("item  ${it+1}"))
        }



        val adapter = AdapterItem(items)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)



        var b=findViewById<Button>(R.id.buttonOkChObject)
        var t=findViewById<EditText>(R.id.editTextObject)
        b.setOnClickListener {
            var newItemName = t.text.toString()
                adapter.addData(newItemName) // add new item
                t.setText("") // clear the input area
            }
        }
    }

