package com.vladiyak.cryptocurrencyapp.fragments.search

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.CoinSearchResponse

data class SearchUiState(
    val list: List<CoinSearchResponse> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false
)
