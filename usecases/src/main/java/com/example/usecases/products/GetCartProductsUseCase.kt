package com.example.usecases.products

import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface GetCartProductsUseCase {

    suspend operator fun invoke(): Flow<List<CartProduct>>
}