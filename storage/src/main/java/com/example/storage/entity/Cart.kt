package com.example.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.storage.converter.RatingObjectConverter

@TypeConverters(RatingObjectConverter::class)
@Entity(tableName = "cart" )
data class CartProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingEntity ,
    val quantity: Int
)
