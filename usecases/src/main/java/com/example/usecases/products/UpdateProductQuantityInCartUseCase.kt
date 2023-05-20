package com.example.usecases.products

import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface UpdateProductQuantityInCartUseCase {

    suspend operator fun invoke(product: Product , quantity : Int)
}