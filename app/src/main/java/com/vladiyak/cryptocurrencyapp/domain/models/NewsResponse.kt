package com.vladiyak.cryptocurrencyapp.domain.models



data class NewsResponse(
    val `data`: List<NewsData>?,
    val message: String?,
    val promoted: List<Any>?,
    val type: Int?
)
