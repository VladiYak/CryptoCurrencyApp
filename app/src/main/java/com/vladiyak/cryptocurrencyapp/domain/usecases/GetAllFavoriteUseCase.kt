package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin

interface GetAllFavoriteUseCase {

    operator fun invoke(): List<FavoriteCoin>
}