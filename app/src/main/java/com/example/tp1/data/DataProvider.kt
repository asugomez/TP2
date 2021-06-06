package com.example.tp1.data

import com.example.tp1.data.api.TodoAPI
import com.example.tp1.data.model.Item
import com.example.tp1.data.model.ItemResponse
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection
//import java.net.URLgit

object DataProvider {
    private val BASE_URL = "http://tomnab.fr/todo-api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service = retrofit.create(TodoAPI::class.java)


    suspend fun getPostFromApi(): List<Item> {
        return service.getItems().items
    }

}