package com.example.usecases.products

import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface GetCachedProductsUseCase {

    suspend operator fun invoke(): Flow<List<Product>>
}