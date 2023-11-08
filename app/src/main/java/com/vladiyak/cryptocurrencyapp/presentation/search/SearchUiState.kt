package com.vladiyak.cryptocurrencyapp.presentation.search

import com.vladiyak.cryptocurrencyapp.domain.models.CoinSearchResponse

data class SearchUiState(
    val list: List<CoinSearchResponse> = emptyList(),
    val message: String = "",
    val isLoading: Boolean = false
)
