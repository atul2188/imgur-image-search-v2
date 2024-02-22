package com.example.imgurimagesearchv2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imgurimagesearchv2.domain.model.Data
import com.example.imgurimagesearchv2.presentation.repository.ImgurRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImgurViewModel @Inject constructor(
    private val imgurRepository: ImgurRepository
): ViewModel() {

    private val _searchedImages = MutableStateFlow<PagingData<Data>>(PagingData.empty())
    val searchedImages = _searchedImages

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.SearchImages -> {
                searchImages(event.query)
            }
        }
    }

    private fun searchImages(query: String) {
        viewModelScope.launch {
            imgurRepository.getImageSearchResults(query)
                .cachedIn(viewModelScope).collect{
                    _searchedImages.value = it
                }
        }
    }
}