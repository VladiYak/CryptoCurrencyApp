package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoiDto(
    val times: Float = 0f,
    val currency: String? = null,
    val percentage: Float = 0f
): Parcelable
