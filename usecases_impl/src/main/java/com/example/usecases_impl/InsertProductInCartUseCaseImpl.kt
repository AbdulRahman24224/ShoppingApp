package com.example.usecases_impl

import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.InsertProductInCartUseCase
import javax.inject.Inject

class InsertProductInCartUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : InsertProductInCartUseCase {

    override suspend fun invoke(product: Product) {
        productsRepository.insertProductInCart(product)
    }


}