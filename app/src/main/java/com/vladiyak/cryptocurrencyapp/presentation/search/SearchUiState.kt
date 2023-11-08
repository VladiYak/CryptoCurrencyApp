package com.vladiyak.cryptocurrencyapp.presentation.search

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.CoinSearchResponse

data class SearchUiState(
    val list: List<CoinSearchResponse> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false
)
