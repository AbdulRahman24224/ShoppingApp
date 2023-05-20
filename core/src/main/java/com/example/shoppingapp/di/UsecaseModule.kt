package com.example.shoppingapp.di

import com.example.usecases.products.GetCachedProductsUseCase
import com.example.usecases.products.GetCartProductsUseCase
import com.example.usecases.products.GetProductsByCategoryUseCase
import com.example.usecases.products.GetRemoteProductsUseCase
import com.example.usecases.products.InsertProductInCartUseCase
import com.example.usecases.products.RemoveProductFromCartUseCase
import com.example.usecases.products.SearchProductsByKeywordUseCase
import com.example.usecases.products.UpdateProductQuantityInCartUseCase
import com.example.usecases_impl.GetCachedProductsUseCaseImpl
import com.example.usecases_impl.GetCartProductsUseCaseImpl
import com.example.usecases_impl.GetProductsByCategoryUseCaseImpl
import com.example.usecases_impl.GetRemoteProductsUseCaseImpl
import com.example.usecases_impl.InsertProductInCartUseCaseImpl
import com.example.usecases_impl.RemoveProductFromCartUseCaseImpl
import com.example.usecases_impl.SearchProductsByKeywordUseCaseImpl
import com.example.usecases_impl.UpdateProductQuantityInCartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetProductsUseCase(
        useCase: GetRemoteProductsUseCaseImpl,
    ): GetRemoteProductsUseCase

    @Binds
    fun bindGetProductsByCategoryUseCase(
        useCase: GetProductsByCategoryUseCaseImpl,
    ): GetProductsByCategoryUseCase

    @Binds
    fun bindSearchProductsByKeywordUseCase(
        useCase: SearchProductsByKeywordUseCaseImpl,
    ): SearchProductsByKeywordUseCase

    @Binds
    fun bindGetCartProductsUseCase(
        useCase: GetCartProductsUseCaseImpl,
    ): GetCartProductsUseCase

    @Binds
    fun bindInsertProductInCartUseCase(
        useCase: InsertProductInCartUseCaseImpl,
    ): InsertProductInCartUseCase

    @Binds
    fun bindRemoveProductFromCartUseCase(
        useCase: RemoveProductFromCartUseCaseImpl,
    ): RemoveProductFromCartUseCase

    @Binds
    fun bindUpdateProductInCartUseCase(
        useCase: UpdateProductQuantityInCartUseCaseImpl,
    ): UpdateProductQuantityInCartUseCase

    @Binds
    fun bindGetCachedProductsUseCase(
        useCase: GetCachedProductsUseCaseImpl,
    ): GetCachedProductsUseCase
}