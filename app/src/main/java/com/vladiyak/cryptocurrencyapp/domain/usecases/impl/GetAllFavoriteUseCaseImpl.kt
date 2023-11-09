package com.vladiyak.cryptocurrencyapp.domain.usecases.impl

import com.vladiyak.cryptocurrencyapp.data.local.mappers.FavoriteEntityMapper
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetAllFavoriteUseCase
import javax.inject.Inject

class GetAllFavoriteUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val favoriteEntityMapper: FavoriteEntityMapper
): GetAllFavoriteUseCase {

    override operator fun invoke(): List<FavoriteCoin> {
        return favoriteEntityMapper.toDomainList(repository.getAllFavorite())
    }
}