package com.vladiyak.cryptocurrencyapp.domain.models

import com.vladiyak.cryptocurrencyapp.data.local.database.entities.FavoriteEntity


data class FavoriteCoin(
    val coinId: String,
    val coinName: String?,
    val symbol: String,
    val price: String?,
    val coinImage: String?,
    val priceChangePercentage7d: Double?,
)

fun FavoriteCoin.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        coinId = coinId,
        coinName = coinName,
        symbol = symbol,
        price = price,
        coinImage = coinImage,
        priceChangePercentage7d = priceChangePercentage7d
    )
}

