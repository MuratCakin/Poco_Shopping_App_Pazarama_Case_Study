package com.muratcakin.poco.data.remote.api

import com.muratcakin.poco.data.model.Cart
import com.muratcakin.poco.data.model.Product
import com.muratcakin.poco.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): Response<Product>

    @GET("users")
    suspend fun getUsers(): Response<User>

    @GET("carts")
    suspend fun getCarts(): Response<Cart>

    @GET("products/id")
    suspend fun getProductDetail(@Path("id") productId: Int): Response<Product>
}