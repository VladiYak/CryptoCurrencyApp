package com.vladiyak.cryptocurrencyapp.api.newapi.dto.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class CoinSearchResponse(
    val id: String,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("large")
    val imageUrl: String,
) : Parcelable
