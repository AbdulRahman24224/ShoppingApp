package com.example.shoppingapp.di

import com.example.repositories.ProductsRepository
import com.example.repositories_impl.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class, SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSignUpRepository(
        repositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository

}