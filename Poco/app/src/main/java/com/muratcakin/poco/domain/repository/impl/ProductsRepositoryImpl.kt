package com.muratcakin.poco.domain.repository.impl

import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.data.remote.source.ProductsRemoteDataSource
import com.muratcakin.poco.data.remote.utils.DataState
import com.muratcakin.poco.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsRemoteDataSource: ProductsRemoteDataSource) :
    ProductsRepository {
    override suspend fun getProductDetail(id: Int): Flow<DataState<ProductDTO>> {
        return productsRemoteDataSource.getProductDetail(id)
    }

    override suspend fun getProducts(): Flow<DataState<List<ProductDTO>>> {
        return productsRemoteDataSource.getProducts()
    }
}