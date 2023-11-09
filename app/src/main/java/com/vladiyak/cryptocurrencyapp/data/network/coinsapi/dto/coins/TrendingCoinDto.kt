package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin


data class TrendingCoinDto(
    val item: TrendingCoinItemDto
)

fun TrendingCoinDto.toTrendingCoin(): TrendingCoin {
    return TrendingCoin(
        item = item.toTrendingCoinItem()
    )
}