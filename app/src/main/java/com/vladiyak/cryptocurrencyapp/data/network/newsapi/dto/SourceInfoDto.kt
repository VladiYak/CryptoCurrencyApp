package com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceInfoDto(
    @Json(name = "img")
    val img: String?,
    @Json(name = "lang")
    val lang: String?,
    @Json(name = "name")
    val name: String?
)
