package com.example.imgurimagesearchv2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.imgurimagesearchv2.data.ImgurPagingSource
import com.example.imgurimagesearchv2.data.remote.ApiService
import com.example.imgurimagesearchv2.presentation.repository.ImgurRepository
import javax.inject.Inject

class ImgurRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ImgurRepository {
    override fun getImageSearchResults(q: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImgurPagingSource(apiService, q) }
        ).flow
}