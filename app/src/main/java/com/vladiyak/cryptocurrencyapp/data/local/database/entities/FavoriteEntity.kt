package com.vladiyak.cryptocurrencyapp.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin

@Entity(tableName = "Favourite_Table")
data class FavoriteEntity(
    @PrimaryKey
    val coinId: String,
    @ColumnInfo(name = "coinName")
    val coinName: String?,
    @ColumnInfo("symbol")
    val symbol: String,
    @ColumnInfo(name = "coinPrice")
    val price: String?,
    @ColumnInfo(name = "coinImage")
    val coinImage: String?,
    @ColumnInfo(name = "priceChangePercentage7d")
    val priceChangePercentage7d: Double?,
)

fun FavoriteEntity.toFavoriteCoin(): FavoriteCoin {
    return FavoriteCoin(
        coinId = coinId,
        coinName = coinName,
        symbol = symbol,
        price = price,
        coinImage = coinImage,
        priceChangePercentage7d = priceChangePercentage7d
    )
}
