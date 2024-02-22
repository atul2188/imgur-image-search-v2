package com.example.imgurimagesearchv2.presentation.repository

import androidx.paging.PagingData
import com.example.imgurimagesearchv2.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImgurRepository {

    fun getImageSearchResults(q:String): Flow<PagingData<Image>>
}