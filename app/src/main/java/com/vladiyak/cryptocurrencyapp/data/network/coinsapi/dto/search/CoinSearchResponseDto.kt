package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CoinSearchResponseDto(
    val id: String,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("large")
    val imageUrl: String,
) : Parcelable
