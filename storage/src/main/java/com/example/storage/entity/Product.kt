package com.example.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.storage.converter.RatingObjectConverter

@TypeConverters(RatingObjectConverter::class)
@Entity(tableName = "productEntity" )
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingEntity
)

data class RatingEntity(
    val rate: Double,
    val count: Int
)