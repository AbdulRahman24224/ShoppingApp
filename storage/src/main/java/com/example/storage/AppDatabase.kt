package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.storage.AppDatabase.Companion.VERSION
import com.example.storage.converter.RatingObjectConverter
import com.example.storage.dao.CartDao
import com.example.storage.dao.ProductsDao
import com.example.storage.entity.CartProductEntity
import com.example.storage.entity.ProductEntity
import com.example.storage.entity.RatingEntity

@TypeConverters(RatingObjectConverter::class)
@Database(
    version = VERSION,
    exportSchema = false,
    entities = [
        ProductEntity::class,
        CartProductEntity::class,
    ],
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao

    abstract fun getProductsDao(): ProductsDao


    companion object {
        const val VERSION = 1
        const val NAME = "database"
    }
}