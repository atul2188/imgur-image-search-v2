package com.example.imgurimagesearchv2.presentation

sealed class SearchEvent {
    data class SearchImages(val query: String) : SearchEvent()
}