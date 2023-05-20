package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product

interface GetRemoteProductsUseCase {

    suspend operator fun invoke(): DataResult<List<Product>>
}