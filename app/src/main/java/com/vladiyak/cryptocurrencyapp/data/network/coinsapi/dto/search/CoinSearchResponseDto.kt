package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.vladiyak.cryptocurrencyapp.domain.models.CoinSearchResponse
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

fun CoinSearchResponseDto.toCoinSearchResponse(): CoinSearchResponse {
    return CoinSearchResponse(
        id = id,
        name = name,
        symbol = symbol,
        marketCapRank = marketCapRank,
        imageUrl = imageUrl
    )
}
