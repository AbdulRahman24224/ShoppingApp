package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetProductsByCategoryUseCase
import com.example.usecases.products.GetProductsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByCategoryUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : GetProductsByCategoryUseCase {


    override suspend fun invoke(category: String): Flow<List<Product>> {
      return productsRepository.getProductsByCategory(category)
    }


}