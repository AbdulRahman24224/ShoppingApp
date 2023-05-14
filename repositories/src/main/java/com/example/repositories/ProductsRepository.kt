package com.example.repositories

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product

interface ProductsRepository {

    suspend fun getRemoteProducts(): DataResult<List<Product>>
}