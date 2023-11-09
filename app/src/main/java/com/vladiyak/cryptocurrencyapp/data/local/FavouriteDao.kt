package com.vladiyak.cryptocurrencyapp.data.local

import androidx.room.*
import com.vladiyak.cryptocurrencyapp.data.local.entities.FavoriteEntity

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM Favourite_Table ORDER BY coinId ASC ")
    fun getAllFavorite(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(data: FavoriteEntity)


    @Delete
    suspend fun deleteFavorite(data: FavoriteEntity)
}