package com.example.usecases_impl

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.GetProductsByCategoryUseCase
import com.example.usecases.products.GetProductsUseCase
import com.example.usecases.products.SearchProductsByKeywordUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsByKeywordUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : SearchProductsByKeywordUseCase {


    override suspend fun invoke(keyword: String): Flow<List<Product>> {
      return productsRepository.queryProducts(keyword)
    }


}