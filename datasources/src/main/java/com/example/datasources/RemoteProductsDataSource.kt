package com.example.datasources

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product

interface RemoteProductsDataSource {

    suspend fun getProducts(): DataResult<List<Product>>


}