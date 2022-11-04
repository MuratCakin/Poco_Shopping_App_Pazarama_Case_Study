package com.muratcakin.poco.data.model


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("products")
    val products: List<ProductX?>?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("__v")
    val v: Int?
)