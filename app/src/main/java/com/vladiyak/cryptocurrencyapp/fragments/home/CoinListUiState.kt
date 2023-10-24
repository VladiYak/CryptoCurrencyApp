package com.vladiyak.cryptocurrencyapp.fragments.home


import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoin


data class CoinListUiState(
    val coinList: List<CoinItem> = emptyList(),
    val trendingCoinList: List<TrendingCoin> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = ""
)