package com.muratcakin.poco.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("title")
    val title: String?
)

data class ProductDTO(
    val id: Int?,
    val image: String?,
    val title: String?,
    val price: Double?,
    val category: String?,
    val description: String?,
    val onCart: Boolean = false
)