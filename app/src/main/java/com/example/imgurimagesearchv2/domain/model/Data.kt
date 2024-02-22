package com.example.imgurimagesearchv2.domain.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("account_url")
    val accountUrl: String,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("datetime")
    val datetime: Int,
    @SerializedName("description")
    val description: Any,
    @SerializedName("downs")
    val downs: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("images_count")
    val imagesCount: Int,
    @SerializedName("is_album")
    val isAlbum: Boolean,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("ups")
    val ups: Int,
    @SerializedName("views")
    val views: Int,
    @SerializedName("vote")
    val vote: Any
)