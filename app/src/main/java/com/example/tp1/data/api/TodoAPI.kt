package com.example.tp1.data.api

import com.example.tp1.data.model.ItemResponse
import com.example.tp1.data.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TodoAPI {
    @GET("/lists?hash=b10ab07311337e6484153b0f5793d516")
    suspend fun getItems(): ItemResponse

    suspend fun getLists(): ListResponse



    /*
    @POST("createList")
    fun createList(

    )

     */
}