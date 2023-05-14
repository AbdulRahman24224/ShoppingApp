package com.example.network.mapper

import com.example.network.models.response.ProductJson
import com.example.network.models.response.RatingJson

import com.example.domain_models.products.Product
import com.example.domain_models.products.Rating

fun ProductJson.toDomain() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toDomain())

fun RatingJson.toDomain() = Rating(rate = rate, count = count)