package com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponseDto(
    @Json(name = "Data")
    val `data`: List<NewsDataDto>?,
    @Json(name = "Message")
    val message: String?,
    @Json(name = "Promoted")
    val promoted: List<Any>?,
    @Json(name = "Type")
    val type: Int?
)
