package com.example.imgurimagesearchv2.data.remote

import com.example.imgurimagesearchv2.domain.model.ImgurResponse
import com.example.imgurimagesearchv2.util.Constants.CLIENT_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization:CLIENT-ID $CLIENT_ID")
    @GET("gallery/search/top/week/{page}")
    suspend fun searchQueriedTopOfTheWeekImages(
        @Path("page")
        pageId: Int,

        @Query("q")
        query: String,

        @Query("q_type")
        queryType: String = "jpg",

        @Query("q_size_px")
        querySize: String = "small"
    ): Response<ImgurResponse>
}