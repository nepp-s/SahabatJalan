package com.example.mycapstone.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.data.api.ApiClient
import com.example.mycapstone.data.repository.DestinationRepository
import com.example.mycapstone.database.Wishlist
import com.example.mycapstone.response.CategoryId
import com.example.mycapstone.response.DestinationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {
    private val _DetailCategory = MutableLiveData<CategoryId?>()
    val detailEvents: MutableLiveData<CategoryId?> = _DetailCategory
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val mNoteRepository: DestinationRepository = DestinationRepository(application)

    suspend fun insert(wishlist: Wishlist) {
        mNoteRepository.insert(wishlist )
    }

    fun findUsername(username: String): LiveData<Wishlist> = mNoteRepository.findUsernameInDb(username)

    fun delete(wishlist: Wishlist) {
        mNoteRepository.delete(wishlist)
    }

    fun findEventActive(id: Int) {
        _isLoading.value = true
        val client = ApiClient.apiService().getDestinations(id)
        client.enqueue(object : Callback<DestinationResponse> {
            override fun onResponse(
                call: Call<DestinationResponse>,
                response: Response<DestinationResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {

                    _DetailCategory.value = response.body()?.name
                } else {
                    Log.e("TAG", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DestinationResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("TAG", "onFailure: ${t.message.toString()}")
            }
        })
    }
}