package com.vladiyak.cryptocurrencyapp.domain.usecases

import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(favoriteCoin: FavoriteCoin)
}