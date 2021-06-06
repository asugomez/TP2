package com.example.tp1.data.api

import com.example.tp1.data.model.ItemResponse
import com.example.tp1.data.model.ListResponse
import retrofit2.http.*

interface TodoAPI {

    //get all the lists from an user
    @GET("lists")
    suspend fun getLists(@Header("hash")hash: String): ListResponse

    // creation of a new list
    @POST("users/{id}/lists?label={label}")
    suspend fun createList(@Path("id")id: Int,
                            @Path("label")label:String,
                           @Header("hash")hash: String): ListResponse

    //creation of a new list from a connected user
    //@POST("lists?label={label}")
    //suspend fun createList

    //
    @GET("lists/{id_list}/items")
    suspend fun getItemsOfAList(@Path("id_list")id_list: Int): ItemResponse

    // cocher un item
    @PUT("lists/{id_list}/items/{id_item}?check=1")
    suspend fun cocherItem(@Path("id_list") id_list: Int,
                            @Path("id_item") id_item: Int)

    @PUT("lists/{id_list}/items/{id_item}")
    suspend fun cocherDecocherItem(@Path("id_list") id_list: Int,
                                   @Path("id_item") id_item: Int,
                                    @Query("check") check: Int)




    /*
    @POST("createList")
    fun createList(

    )

     */
}