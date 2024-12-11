package com.example.mycapstone.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mycapstone.database.WishlistDao
import com.example.mycapstone.data.api.ApiClient
import com.example.mycapstone.database.Wishlist
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class DestinationRepository(private val Wishlistdao: Application) {
    private val executorService : ExecutorService = Executor.newSingleThreadExecutor()
    
    val destinations: Flow<List<Wishlist>> = Wishlistdao.getAllDestinations()

    suspend fun refreshDestinations() {
        val destinationsFromApi = ApiClient.apiService.getDestinations()

        Wishlistdao.deleteAll()
        Wishlistdao.insertAll(destinationsFromApi)
    }
    fun insert(wishlist: Wishlist) {
        executorService.execute { WishlistDao.insertAll(wishlist) }
    }

    fun delete(wishlist: Wishlist) {
        executorService.execute { WishlistDao.deleteAll(wishlist) }
    }

    fun findUsernameInDb(username: String): LiveData<Wishlist> {
        return WishlistDao.getAllDestinations(destinations)
    }

}