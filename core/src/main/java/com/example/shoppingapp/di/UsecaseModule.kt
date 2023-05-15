package com.example.shoppingapp.di

import com.example.usecases.products.GetProductsByCategoryUseCase
import com.example.usecases.products.GetProductsUseCase
import com.example.usecases.products.SearchProductsByKeywordUseCase
import com.example.usecases_impl.GetProductsByCategoryUseCaseImpl
import com.example.usecases_impl.GetProductsUseCaseImpl
import com.example.usecases_impl.SearchProductsByKeywordUseCaseImpl
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
        useCase: GetProductsUseCaseImpl,
    ): GetProductsUseCase

    @Binds
    fun bindGetProductsByCategoryUseCase(
        useCase: GetProductsByCategoryUseCaseImpl,
    ): GetProductsByCategoryUseCase

    @Binds
    fun bindSearchProductsByKeywordUseCase(
        useCase: SearchProductsByKeywordUseCaseImpl,
    ): SearchProductsByKeywordUseCase

}