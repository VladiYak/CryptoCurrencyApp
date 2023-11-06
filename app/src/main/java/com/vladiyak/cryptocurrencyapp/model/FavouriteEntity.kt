package com.vladiyak.cryptocurrencyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Favourite_Table")
data class FavouriteEntity(
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
