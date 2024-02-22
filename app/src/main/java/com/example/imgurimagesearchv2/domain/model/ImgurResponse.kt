package com.example.imgurimagesearchv2.domain.model


import com.google.gson.annotations.SerializedName

data class ImgurResponse(
    @SerializedName("data")
    val imgurData: Data,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)