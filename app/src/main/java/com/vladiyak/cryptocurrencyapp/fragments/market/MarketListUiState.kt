package com.vladiyak.cryptocurrencyapp.fragments.market

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.markets.Exchange

data class MarketListUiState(
    val list: List<Exchange> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = ""
)