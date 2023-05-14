package com.example.network.service

import com.example.network.models.response.ProductJson
import retrofit2.http.GET


interface ProductsService {

    @GET("products")
    suspend fun getProducts(): GenericResponse<List<ProductJson>>


}