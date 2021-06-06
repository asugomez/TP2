package com.example.tp1.data

import com.example.tp1.data.api.ProductHuntService
import com.example.tp1.data.model.Post
import com.example.tp1.data.model.PostsResponse
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object DataProvider {
    private val BASE_URL = "http://tomnab.fr/todo-api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service = retrofit.create(ProductHuntService::class.java)


    suspend fun getPostFromApi(): List<Post> {
        return service.getPosts().posts
    }

}