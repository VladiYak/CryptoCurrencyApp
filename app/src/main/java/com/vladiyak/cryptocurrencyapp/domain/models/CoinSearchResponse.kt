package com.vladiyak.cryptocurrencyapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinSearchResponse(
    val id: String,
    val name: String,
    val symbol: String,
    val marketCapRank: Int,
    val imageUrl: String,
): Parcelable
