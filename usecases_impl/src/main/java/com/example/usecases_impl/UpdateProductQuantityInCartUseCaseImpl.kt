package com.example.usecases_impl

import com.example.domain_models.products.Product
import com.example.repositories.ProductsRepository
import com.example.usecases.products.UpdateProductQuantityInCartUseCase
import javax.inject.Inject

class UpdateProductQuantityInCartUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : UpdateProductQuantityInCartUseCase {



    override suspend fun invoke(product: Product, quantity: Int) {
       productsRepository.updateProductQuantity(quantity ,product.id.toString() )
    }


}