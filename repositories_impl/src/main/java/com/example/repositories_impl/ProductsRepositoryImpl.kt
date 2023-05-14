package com.example.repositories_impl

import com.example.datasources.RemoteProductsDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteProductsDataSource: RemoteProductsDataSource
) : ProductsRepository {


    override suspend fun getRemoteProducts(): DataResult<List<Product>> {
        return remoteProductsDataSource.getProducts()
    }


}
