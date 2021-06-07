package com.example.tp1.data

import com.example.tp1.data.api.TodoAPI
import com.example.tp1.data.model.Item
import com.example.tp1.data.model.ItemResponse
import com.example.tp1.data.model.ListResponse
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

//import java.net.URLgit

object DataProvider {
    //private val hashcode = "b10ab07311337e6484153b0f5793d516"
    //private val id_list = 2
    private val API_URL = "http://tomnab.fr/todo-api/"


    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val service = getRetrofitInstance().create(TodoAPI::class.java)


    suspend fun connexion(pseudo: String, pass: String): String{
        return service.connexion(pseudo, pass)
    }

    suspend fun getListsFromApi(hash: String): List<com.example.tp1.data.model.List>{
        return service.getLists(hash).lists
    }

    suspend fun createList(id_user: Int, label: String, hash:String): List<com.example.tp1.data.model.List>{
        return service.createList(id_user, label, hash).lists
    }
    suspend fun getItemsOfAList(id_list: Int, hash: String): List<Item> {
        return service.getItemsOfAList(id_list, hash).items
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
