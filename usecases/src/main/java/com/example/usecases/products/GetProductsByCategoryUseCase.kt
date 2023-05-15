package com.example.usecases.products

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface GetProductsByCategoryUseCase {

    suspend operator fun invoke(category :String): Flow<List<Product>>
}