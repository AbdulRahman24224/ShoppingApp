package com.example.repositories

import com.example.domain_models.network.DataResult
import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getRemoteProducts(): DataResult<List<Product>>

    suspend fun insertProducts(products: List<Product>)

    suspend fun getCachedProducts(): Flow<List<Product>>

    suspend fun getProductsByCategory(categories: List<String>): Flow<List<Product>>

    suspend fun queryProducts(query: String ,categories: List<String>): Flow<List<Product>>


    suspend fun insertProductInCart(product: Product)


    suspend fun getAllCartItems(): Flow<List<CartProduct>>

    suspend fun updateProductQuantity(quantity: Int, productId: String)

    suspend fun removeProductFromCart(productId: Int):Int

    suspend fun clearCart() : Int
}