package com.vladiyak.cryptocurrencyapp.domain.models

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoinItemDto

data class TrendingCoin(
    val item: TrendingCoinItem
)
