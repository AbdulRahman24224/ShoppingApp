package com.example.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storage.entity.CartProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertProduct(cardInfo: CartProductEntity)

    @Query("SELECT * from cart")
     fun getAllCartItems(): Flow<List<CartProductEntity>>

    @Query("UPDATE cart set quantity = :quantity where id = :productId")
     fun updateProductQuantity(quantity: Int, productId: String)

    @Query("DELETE FROM cart WHERE id = :productId")
     fun removeProductFromCart(productId: Int) :Int

    @Query("SELECT * FROM cart WHERE id = :itemId LIMIT 1")
    fun returnIfExists(itemId: Int): CartProductEntity?

    @Query("DELETE FROM cart ")
     fun clearAll():Int

}