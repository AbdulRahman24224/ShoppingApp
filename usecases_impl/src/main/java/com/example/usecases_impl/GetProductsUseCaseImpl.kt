package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetProductsUseCase
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : GetProductsUseCase {


    override suspend fun invoke(): DataResult<List<Product>> {

        return productsRepository.getRemoteProducts()
    }

}