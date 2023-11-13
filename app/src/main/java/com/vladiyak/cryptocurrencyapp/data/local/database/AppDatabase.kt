package com.vladiyak.cryptocurrencyapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladiyak.cryptocurrencyapp.data.local.database.entities.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao
}