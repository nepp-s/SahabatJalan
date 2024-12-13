package com.example.mycapstone.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mycapstone.database.WishlistDao
import com.example.mycapstone.data.api.ApiClient
import com.example.mycapstone.database.AppDatabase
import com.example.mycapstone.database.Wishlist
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DestinationRepository(application: Application) {
    private val mFavoriteDao: WishlistDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = AppDatabase.getDatabase(application)
        mFavoriteDao = db.destinationDao()
    }

    fun getAllFavorites(): LiveData<List<Wishlist>> = mFavoriteDao.getAllFavorites()

    fun insert(wishlist: Wishlist) {
        executorService.execute { mFavoriteDao.insertFavorite(wishlist) }
    }

    fun delete(wishlist: Wishlist) {
        executorService.execute { mFavoriteDao.deleteFavorite(wishlist) }
    }

    fun findUsernameInDb(username: String): LiveData<Wishlist> {
        return mFavoriteDao.getFavoriteUserByUsername(username)
    }

}