package com.example.mycapstone.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT * FROM destination")
    fun getAllDestinations(): Flow<List<Wishlist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(wishlist: List<Wishlist>)

    @Query("DELETE FROM destination")
    suspend fun deleteAll()


}