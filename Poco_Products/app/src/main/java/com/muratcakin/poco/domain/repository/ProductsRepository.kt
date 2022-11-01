package com.muratcakin.poco.domain.repository

import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductDetail(productId: Int): Flow<DataState<Product>>
    suspend fun getProducts(): Flow<DataState<List<Product>>>
}