package com.muratcakin.poco.data.remote.source

import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductsRemoteDataSource {
    suspend fun getProductDetail(id: Int): Flow<DataState<Product>>
    suspend fun getProducts(): Flow<DataState<List<Product>>>
}