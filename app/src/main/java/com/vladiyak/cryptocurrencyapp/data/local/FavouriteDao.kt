package com.vladiyak.cryptocurrencyapp.data.local

import androidx.room.*

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM Favourite_Table ORDER BY coinId ASC ")
    fun getAllFavourite(): List<FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(data: FavouriteEntity)

    @Delete
    suspend fun deleteFavourite(data: FavouriteEntity)
}