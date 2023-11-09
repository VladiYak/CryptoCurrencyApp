package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.local.mappers.FavoriteEntityMapper
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.DeleteFavoriteUseCase
import javax.inject.Inject

class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val favoriteEntityMapper: FavoriteEntityMapper
): DeleteFavoriteUseCase {

    override suspend operator fun invoke(favoriteCoin: FavoriteCoin) {
        repository.deleteFavorite(favoriteEntityMapper.mapFromDomainModel(favoriteCoin))
    }
}