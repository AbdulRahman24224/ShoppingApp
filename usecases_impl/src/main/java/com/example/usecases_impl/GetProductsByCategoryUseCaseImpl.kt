package com.example.usecases_impl

import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetProductsByCategoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByCategoryUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : GetProductsByCategoryUseCase {


    override suspend fun invoke(categories: List<String>): Flow<List<Product>> {
      return productsRepository.getProductsByCategory(categories)
    }


}