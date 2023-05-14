package com.example.network.models.response

import com.google.gson.annotations.SerializedName


data class ProductJson(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("rating")
    val rating: RatingJson
)

data class RatingJson(
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("count")
    val count: Int
)