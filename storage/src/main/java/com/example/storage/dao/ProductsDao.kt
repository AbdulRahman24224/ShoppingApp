package com.example.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storage.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
 interface ProductsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * from productEntity")
     fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * from productEntity where category In (:categories)")
     fun getProductsByCategory(categories: List<String>): Flow<List<ProductEntity>>


    @Query("SELECT * FROM productEntity WHERE (title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%') AND category IN (:categories)")
     fun queryProductsWithCategories(query: String , categories: List<String>): Flow<List<ProductEntity>>

     @Query("SELECT * FROM productEntity WHERE (title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%') ")
     fun queryProducts(query: String ): Flow<List<ProductEntity>>

}