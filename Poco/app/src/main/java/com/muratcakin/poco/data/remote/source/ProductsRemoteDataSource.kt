package com.muratcakin.poco.data.remote.source

import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductsRemoteDataSource {
    suspend fun getProductDetail(id: Int): Flow<DataState<ProductDTO>>
    suspend fun getProducts(): Flow<DataState<List<ProductDTO>>>
}