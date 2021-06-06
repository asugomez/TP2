package com.example.tp1.data

import com.example.tp1.data.api.TodoAPI
import com.example.tp1.data.model.Item
import com.example.tp1.data.model.ItemResponse
import com.example.tp1.data.model.ListResponse
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

//import java.net.URLgit

object DataProvider {
    private val hashcode = "b10ab07311337e6484153b0f5793d516"
    private val API_URL = "http://tomnab.fr/todo-api/"


    private val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service = retrofit.create(TodoAPI::class.java)


    suspend fun getListsFromApi(): List<com.example.tp1.data.model.List>{
        return service.getLists().lists
    }
    suspend fun getItemsFromApi(): List<Item> {
        return service.getItems().items
    }




}

/*
    val gson = Gson()

    fun getListsFromApi(): List<com.example.tp1.data.model.List>{
        val json = getCall("")
        val listResponse = gson.fromJson(json, ListResponse::class.java)
        return listResponse.lists
    }

    fun getItemsFromApi(): List<Item>{
        val json = getCall("items")
        val itemResponse = gson.fromJson(json, ItemResponse::class.java)
        return itemResponse.items
    }


    private fun getCall(url:String): String?{
        var urlConnection: HttpURLConnection?=null
        var reader: BufferedReader?=null
        try{
            urlConnection = URL(API_URL+url).openConnection() as HttpURLConnection
            //urlConnection.hashCode(hashcode)
            urlConnection.requestMethod = "GET"
            urlConnection.connect()
            reader=urlConnection.inputStream?.bufferedReader()
            return reader?.readText()
        }finally {
            urlConnection?.disconnect()
            reader?.close()
        }

    }

    private fun putCall(): String?{
        var urlConnection: HttpURLConnection?=null
        var reader: BufferedReader?=null
        try{
            urlConnection = URL(API_URL).openConnection() as HttpURLConnection
            urlConnection.requestMethod = "PUT"
            urlConnection.connect()
            reader=urlConnection.inputStream?.bufferedReader()
            return reader?.readText()
        }finally {
            urlConnection?.disconnect()
            reader?.close()
        }

    }
 */
