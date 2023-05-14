package com.example.network.datasource_impl

import com.example.datasources.RemoteProductsDataSource
import com.example.domain_models.network.DataResult
import com.example.domain_models.products.Product
import com.example.network.mapper.toDomain
import com.example.network.models.NetworkResponse
import com.example.network.service.ProductsService
import com.example.network.utils.toException
import javax.inject.Inject

class RemoteProductsDataSourceImpl @Inject constructor(
    private val service: ProductsService,

) : RemoteProductsDataSource {


    override suspend fun getProducts(): DataResult<List<Product>> {
        return try {
            service.getProducts().let {
                when (it) {
                    is NetworkResponse.ApiError -> {
                        DataResult.Failure(it.body.toException())
                    }

                    is NetworkResponse.NetworkError -> {
                        DataResult.Failure(RuntimeException(it.error.localizedMessage))
                    }

                    is NetworkResponse.Success -> {
                       DataResult.Success(it.body.map { productJson ->  productJson.toDomain() })
                    }

                    is NetworkResponse.UnknownError -> {
                        DataResult.Failure(RuntimeException(it.error.localizedMessage))
                    }
                }
            }
        } catch (e: Throwable) {
            DataResult.Failure(RuntimeException(e))
        }
    }


}


