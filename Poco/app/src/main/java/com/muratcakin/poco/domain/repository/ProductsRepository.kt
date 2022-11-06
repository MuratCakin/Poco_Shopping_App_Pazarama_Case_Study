package com.muratcakin.poco.domain.repository

import com.muratcakin.poco.data.model.ProductDTO
import com.muratcakin.poco.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductDetail(id: Int): Flow<DataState<ProductDTO>>
    suspend fun getProducts(): Flow<DataState<List<ProductDTO>>>
}