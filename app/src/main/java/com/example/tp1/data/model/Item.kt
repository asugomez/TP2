package com.example.tp1.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    // user, mdp, lists of the user
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("tagline")
    val subTitle: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
)

data class Thumbnail(
    @SerializedName("image_url")
    val imageUrl: String
)

