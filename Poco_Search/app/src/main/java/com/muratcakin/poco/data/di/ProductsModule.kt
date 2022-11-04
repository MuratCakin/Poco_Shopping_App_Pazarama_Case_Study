package com.muratcakin.poco.data.di

import com.muratcakin.poco.data.remote.api.ProductsService
import com.muratcakin.poco.data.remote.source.ProductsRemoteDataSource
import com.muratcakin.poco.data.remote.source.impl.ProductsRemoteDataSourceImpl
import com.muratcakin.poco.domain.repository.ProductsRepository
import com.muratcakin.poco.domain.repository.impl.ProductsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductsModule {

    @Singleton
    @Provides
    fun provideProductsService(retrofit: Retrofit) = retrofit.create(ProductsService::class.java)

    @Singleton
    @Provides
    fun provideProductsRemoteDataSource(productsService: ProductsService): ProductsRemoteDataSource =
        ProductsRemoteDataSourceImpl(productsService)

    @Singleton
    @Provides
    fun provideProductsRepository(productsRemoteDataSource: ProductsRemoteDataSource): ProductsRepository =
        ProductsRepositoryImpl(productsRemoteDataSource)
}