package com.example.datasources

import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import kotlinx.coroutines.flow.Flow

interface DatabaseDataSource {

    suspend fun insertProducts(products: List<Product>)

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun getProductsByCategory(category: String): Flow<List<Product>>


    suspend fun queryProducts(query: String): Flow<List<Product>>


    suspend fun insertProductInCart(product: Product)


    suspend fun getAllCartItems(): Flow<List<CartProduct>>

    suspend fun updateProductQuantity(quantity: Int, productId: String)

    suspend fun removeProductFromCart(productId: Int):Int

    suspend fun clearCart() :Int

}