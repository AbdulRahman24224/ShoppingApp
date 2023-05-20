package com.example.usecases_impl

import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetCachedProductsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedProductsUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : GetCachedProductsUseCase {


    override suspend fun invoke(): Flow<List<Product>> {
         return productsRepository.getCachedProducts()
    }


}