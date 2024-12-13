package com.example.mycapstone.ui.fragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.data.repository.DestinationRepository
import com.example.mycapstone.database.Wishlist

class FavoriteViewModel(application: Application): ViewModel() {

    private val mFavoriteRepository: DestinationRepository = DestinationRepository(application)

    fun getAllFavorites(): LiveData<List<Wishlist>> = mFavoriteRepository.getAllFavorites()
}