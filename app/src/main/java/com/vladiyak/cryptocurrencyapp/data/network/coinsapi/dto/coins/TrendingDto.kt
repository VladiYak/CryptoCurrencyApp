package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import com.vladiyak.cryptocurrencyapp.domain.models.Trending


data class TrendingDto(
    val coins: List<TrendingCoinDto>
)

fun TrendingDto.toTrending(): Trending {
    return Trending(
        coins = coins.map { it.toTrendingCoin() }
    )
}
