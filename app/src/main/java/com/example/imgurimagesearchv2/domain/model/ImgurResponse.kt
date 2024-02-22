package com.example.imgurimagesearchv2.domain.model


import com.google.gson.annotations.SerializedName

data class ImgurResponse(
    @SerializedName("data")
    val imgurData: List<Data>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)