package com.example.storage.mappers

import com.example.domain_models.products.Product
import com.example.domain_models.products.Rating
import com.example.storage.entity.ProductEntity
import com.example.storage.entity.RatingEntity

fun Product.toEntity() = com.example.storage.entity.ProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toEntity()
)

fun Rating.toEntity() = com.example.storage.entity.RatingEntity(
    rate = rate,
    count = count
)

fun ProductEntity.toDomain() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toDomain()
)

fun RatingEntity.toDomain() = Rating(
    rate = rate,
    count = count
)