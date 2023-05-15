package com.example.storage.datasource_impl

import com.example.datasources.DatabaseDataSource
import com.example.domain_models.products.CartProduct
import com.example.domain_models.products.Product
import com.example.storage.dao.CartDao
import com.example.storage.dao.ProductsDao
import com.example.storage.mappers.toCartEntity
import com.example.storage.mappers.toDomain
import com.example.storage.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseDataSourceImpl @Inject constructor(
    private val cartDao: CartDao,
    private val productsDao: ProductsDao,
) : DatabaseDataSource {


    override suspend fun insertProducts(products: List<Product>) = productsDao.insertProducts(products.map { it.toEntity() })

    override suspend fun getAllProducts(): Flow<List<Product>> = productsDao.getAllProducts().map { it.map { it.toDomain() }}

    override suspend fun getProductsByCategory(category: String): Flow<List<Product>> {
       return productsDao.getProductsByCategory(category).map { it.map { it.toDomain() } }
    }

    override suspend fun queryProducts(query: String): Flow<List<Product>> {
       return productsDao.queryProducts(query).map { it.map { it.toDomain() } }
    }

    override suspend fun insertProductInCart(product: Product) {
        return cartDao.insertProduct(product.toCartEntity())
    }

    override suspend fun getAllCartItems(): Flow<List<CartProduct>> {
       return cartDao.getAllCartItems().map { it.map { it.toDomain() } }
    }

    override suspend fun updateProductQuantity(quantity: Int, productId: String) {
        return cartDao.updateProductQuantity(quantity, productId)
    }

    override suspend fun removeProductFromCart(productId: Int):Int {
       return cartDao.removeProductFromCart(productId)
    }

    override suspend fun clearCart(): Int = cartDao.clearAll()




}
