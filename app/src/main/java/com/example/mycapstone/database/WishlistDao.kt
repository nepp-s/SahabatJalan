package com.example.mycapstone.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(destination: Wishlist)

    @Delete
    fun deleteFavorite(favorite: Wishlist)

    @Query("SELECT * FROM destination WHERE id = :eventId")
    fun getFavoriteById(eventId: Int): Wishlist?

    @Query("SELECT * FROM destination")
    fun getAllFavorites(): LiveData<List<Wishlist>>

    @Query("SELECT * FROM destination WHERE name = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<Wishlist>

}