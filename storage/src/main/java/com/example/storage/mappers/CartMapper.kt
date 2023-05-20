package com.example.storage.mappers

import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import com.example.storage.entity.CartProductEntity

fun Product.toCartEntity() = com.example.storage.entity.CartProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toEntity(),
    quantity = 1
)

fun CartProductEntity.toDomain() = CartProduct(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.toDomain() ,
    quantity = quantity
)
