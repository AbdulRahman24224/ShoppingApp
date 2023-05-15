package com.example.repositories_impl

import com.example.datasources.DatabaseDataSource
import com.example.datasources.RemoteProductsDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteProductsDataSource: RemoteProductsDataSource ,
    private val databaseDataSource: DatabaseDataSource
) : ProductsRepository {


    override suspend fun getRemoteProducts(): DataResult<List<Product>> {
        return remoteProductsDataSource.getProducts()
    }

    override suspend fun insertProducts(products: List<Product>) {
        databaseDataSource.insertProducts(products)
    }

    override suspend fun getAllProducts(): Flow<List<Product>> {
      return databaseDataSource.getAllProducts()
    }

    override suspend fun getProductsByCategory(category: String): Flow<List<Product>> {
        return databaseDataSource.getProductsByCategory(category)
    }

    override suspend fun queryProducts(query: String): Flow<List<Product>> {
      return databaseDataSource.queryProducts(query)
    }

    override suspend fun insertProductInCart(product: Product) {
        return databaseDataSource.insertProductInCart(product)
    }

    override suspend fun getAllCartItems(): Flow<List<CartProduct>> {
        return databaseDataSource.getAllCartItems()
    }

    override suspend fun updateProductQuantity(quantity: Int, productId: String) {
       return databaseDataSource.updateProductQuantity(quantity,productId)
    }

    override suspend fun removeProductFromCart(productId: Int): Int {
       return databaseDataSource.removeProductFromCart(productId)
    }

    override suspend fun clearCart() :Int{
        return databaseDataSource.clearCart()
    }


}
