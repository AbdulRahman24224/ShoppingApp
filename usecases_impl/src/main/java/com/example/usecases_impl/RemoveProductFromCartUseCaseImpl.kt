package com.example.usecases_impl

import com.example.repositories.ProductsRepository
import com.example.usecases.products.RemoveProductFromCartUseCase
import javax.inject.Inject

class RemoveProductFromCartUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : RemoveProductFromCartUseCase {


    override suspend fun invoke(productId: Int) {
       productsRepository.removeProductFromCart(productId)
    }


}