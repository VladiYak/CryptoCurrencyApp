package com.vladiyak.cryptocurrencyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladiyak.cryptocurrencyapp.data.local.entities.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao
}