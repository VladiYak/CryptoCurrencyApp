package com.vladiyak.cryptocurrencyapp.presentation.home


import com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.coins.TrendingCoin


data class CoinListUiState(
    val coinList: List<CoinItem> = emptyList(),
    val trendingCoinList: List<TrendingCoin> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = ""
)