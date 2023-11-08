package com.vladiyak.cryptocurrencyapp.domain.models


import com.vladiyak.cryptocurrencyapp.data.network.newsapi.dto.SourceInfoDto

data class NewsData(
    val body: String?,
    val categories: String?,
    val downvotes: String?,
    val guid: String?,
    val id: String?,
    val imageurl: String?,
    val lang: String?,
    val publishedOn: Int?,
    val source: String?,
    val sourceInfo: SourceInfo?,
    val tags: String?,
    val title: String?,
    val upvotes: String?,
    val url: String?
)
