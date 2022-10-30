package com.muratcakin.poco.data.model


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("productId")
    val productId: Int?,
    @SerializedName("quantity")
    val quantity: Int?
)