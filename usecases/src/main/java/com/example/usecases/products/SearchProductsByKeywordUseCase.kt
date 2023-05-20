package com.example.usecases.products

import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface SearchProductsByKeywordUseCase {

    suspend operator fun invoke(keyword :String , categories : List<String>): Flow<List<Product>>
}