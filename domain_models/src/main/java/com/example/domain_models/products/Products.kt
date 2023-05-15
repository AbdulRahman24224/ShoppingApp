package com.example.domain_models.products

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class CartProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating ,
    val quantity: Int
)

data class Rating(
    val rate: Double,
    val count: Int
)