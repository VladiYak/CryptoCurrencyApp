package com.vladiyak.cryptocurrencyapp.presentation.home


import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinDto
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin


data class CoinListUiState(
    val coinList: List<CoinItem> = emptyList(),
    val trendingCoinList: List<TrendingCoin> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = ""
)