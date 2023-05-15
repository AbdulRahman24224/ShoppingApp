package com.example.storage.converter

import androidx.room.TypeConverter
import com.example.storage.entity.RatingEntity
import com.google.gson.Gson

object RatingObjectConverter {

    @TypeConverter
    @JvmStatic
    fun ratingEntityToString(ratingEntity: RatingEntity?): String =
        Gson().toJson(ratingEntity ?: "{}")

    @TypeConverter
    @JvmStatic
    fun stringToRatingEntity(value: String): RatingEntity =
        Gson().fromJson(value, RatingEntity::class.java)
}