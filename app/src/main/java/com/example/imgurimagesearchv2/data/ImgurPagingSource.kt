package com.example.imgurimagesearchv2.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imgurimagesearchv2.data.remote.ApiService
import com.example.imgurimagesearchv2.domain.model.Data
import com.example.imgurimagesearchv2.domain.model.Image
import javax.inject.Inject

private const val IMGUR_STARTING_PAGE_INDEX = 1
class ImgurPagingSource @Inject constructor(
    private val apiService: ApiService,
    private val queryString: String
): PagingSource<Int, Image>(){
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            val position = params.key ?: IMGUR_STARTING_PAGE_INDEX
            val response = apiService.searchQueriedTopOfTheWeekImages(pageId = position, query = queryString)
            if ( response.isSuccessful){
                val images = response.body()?.imgurData?.images
                LoadResult.Page(
                    data = images!!,
                    prevKey = if (position == 0) null else position -1,
                    nextKey = if (images.isEmpty()) null else position +1
                )
            }
            else
            {
                LoadResult.Error(Exception())
            }
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}