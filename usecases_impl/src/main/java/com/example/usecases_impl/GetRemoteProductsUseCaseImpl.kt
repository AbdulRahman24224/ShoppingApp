package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetRemoteProductsUseCase
import javax.inject.Inject

class GetRemoteProductsUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : GetRemoteProductsUseCase {


    override suspend fun invoke(): DataResult<List<Product>> {

        val result = productsRepository.getRemoteProducts()

        if ( result is DataResult.Success ) productsRepository.insertProducts(result.data)


        return productsRepository.getRemoteProducts()
    }

}