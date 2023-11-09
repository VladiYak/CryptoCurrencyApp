package com.vladiyak.cryptocurrencyapp.data.local

import androidx.room.*

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM Favourite_Table ORDER BY coinId ASC ")
    fun getAllFavorite(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(data: FavoriteEntity)


    @Delete
    suspend fun deleteFavorite(data: FavoriteEntity)
}