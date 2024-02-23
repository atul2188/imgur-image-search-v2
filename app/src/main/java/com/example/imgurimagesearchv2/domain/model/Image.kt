package com.example.imgurimagesearchv2.domain.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("animated")
    val animated: Boolean,
    @SerializedName("bandwidth")
    val bandwidth: Long,
    @SerializedName("datetime")
    val datetime: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("views")
    val views: Int,
    @SerializedName("width")
    val width: Int
)