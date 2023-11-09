package com.vladiyak.cryptocurrencyapp.di

import com.vladiyak.cryptocurrencyapp.domain.usecases.AddFavoriteUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.DeleteFavoriteUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetAllFavoriteUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetCoinDetailsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetCoinsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetLatestNewsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetMarketChartUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.GetTrendingCoinsUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.SearchUseCase
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.AddFavoriteUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.DeleteFavoriteUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetAllFavoriteUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetCoinDetailsUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetCoinsUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetLatestNewsUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetMarketChartUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.GetTrendingCoinsUseCaseImpl
import com.vladiyak.cryptocurrencyapp.domain.usecases.impl.SearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {
    @ViewModelScoped
    @Binds
    abstract fun bindAddFavoriteUseCase(impl: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindDeleteFavoriteUseCase(impl: DeleteFavoriteUseCaseImpl): DeleteFavoriteUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetAllFavoriteUseCase(impl: GetAllFavoriteUseCaseImpl): GetAllFavoriteUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetCoinDetailsUseCase(impl: GetCoinDetailsUseCaseImpl): GetCoinDetailsUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetCoinsUseCase(impl: GetCoinsUseCaseImpl): GetCoinsUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetLatestNewsUseCase(impl: GetLatestNewsUseCaseImpl): GetLatestNewsUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetMarketChartUseCase(impl: GetMarketChartUseCaseImpl): GetMarketChartUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetTrendingCoinsUseCase(impl: GetTrendingCoinsUseCaseImpl): GetTrendingCoinsUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindSearchUseCase(impl: SearchUseCaseImpl): SearchUseCase

}
