package com.vladiyak.cryptocurrencyapp.domain.models

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinDto

data class Trending(
    val coins: List<TrendingCoin>
)
