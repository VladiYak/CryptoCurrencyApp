package com.vladiyak.cryptocurrencyapp.di

import android.content.Context
import com.vladiyak.cryptocurrencyapp.data.local.preferences.PreferenceStorage
import com.vladiyak.cryptocurrencyapp.data.local.preferences.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun providePreferenceStorage(@ApplicationContext context: Context): PreferenceStorage = SharedPreferenceStorage(context)
}