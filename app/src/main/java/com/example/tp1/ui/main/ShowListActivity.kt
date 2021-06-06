package com.example.tp1.ui.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1.R
import com.example.tp1.data.model.Item
import com.example.tp1.ui.main.adapter.AdapterItem


class ShowListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        var list_name: String? = intent.getStringExtra("list")
        this.title = "Items of $list_name"

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewChObject)
        val items: MutableList<Item> = mutableListOf()

        /*repeat(3){
            items.add(Item("item  ${it+1}"))
        }*/


        val adapter = AdapterItem(items)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        val b = findViewById<Button>(R.id.buttonOkChObject)
        val t = findViewById<EditText>(R.id.editTextObject)
        /*
        b.setOnClickListener {
            val newItemName = t.text.toString()
                adapter.addData(newItemName) // add new item
                t.setText("") // clear the input area
            }
         */
        }
    }

