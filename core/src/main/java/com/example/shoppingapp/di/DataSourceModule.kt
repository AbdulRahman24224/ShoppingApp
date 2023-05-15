package com.example.shoppingapp.di


import com.example.datasources.DatabaseDataSource
import com.example.datasources.RemoteProductsDataSource
import com.example.network.datasource_impl.RemoteProductsDataSourceImpl
import com.example.storage.datasource_impl.DatabaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn( SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindRemoteProductDataSource(
        userDataSourceImpl: RemoteProductsDataSourceImpl
    ): RemoteProductsDataSource

    @Binds
    fun bindDatabaseDataSource(
        userDataSourceImpl: DatabaseDataSourceImpl
    ): DatabaseDataSource


}