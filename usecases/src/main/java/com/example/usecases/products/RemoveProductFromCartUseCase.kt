package com.example.usecases.products

import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface RemoveProductFromCartUseCase {

    suspend operator fun invoke(productId: Int)
}