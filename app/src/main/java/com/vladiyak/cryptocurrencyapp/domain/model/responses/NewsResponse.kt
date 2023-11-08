package com.vladiyak.cryptocurrencyapp.domain.model.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vladiyak.cryptocurrencyapp.domain.model.NewsData

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "Data")
    val `data`: List<NewsData>?,
    @Json(name = "Message")
    val message: String?,
    @Json(name = "Promoted")
    val promoted: List<Any>?,
    @Json(name = "Type")
    val type: Int?
)
