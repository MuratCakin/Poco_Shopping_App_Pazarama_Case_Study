package com.muratcakin.poco.data.remote.source.impl

import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.remote.api.ProductsService
import com.muratcakin.poco.data.remote.source.ProductsRemoteDataSource
import com.muratcakin.poco.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRemoteDataSourceImpl @Inject constructor(private val productsService: ProductsService) :
    BaseRemoteDataSource(), ProductsRemoteDataSource {
    override suspend fun getProductDetail(id: Int): Flow<DataState<Product>> {
        return getResult { productsService.getProductDetail(id) }
    }

    override suspend fun getProducts(): Flow<DataState<List<Product>>> {
        return getResult { productsService.getProducts() }
    }
}