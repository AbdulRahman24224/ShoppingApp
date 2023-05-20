package com.example.usecases_impl

import com.example.domain_models.products.CartProduct
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetCartProductsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartProductsUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : GetCartProductsUseCase {


    override suspend fun invoke(): Flow<List<CartProduct>> {
         return productsRepository.getAllCartItems()
    }


}