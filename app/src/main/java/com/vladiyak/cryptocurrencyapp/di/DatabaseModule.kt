package com.vladiyak.cryptocurrencyapp.di

import android.content.Context
import androidx.room.Room
import com.vladiyak.cryptocurrencyapp.data.local.AppDatabase
import com.vladiyak.cryptocurrencyapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideFavouriteDao(appDatabase: AppDatabase) = appDatabase.favouriteDao()

}