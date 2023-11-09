package com.vladiyak.cryptocurrencyapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
