package com.example.mycapstone.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.data.api.ApiClient
import com.example.mycapstone.data.repository.DestinationRepository
import com.example.mycapstone.database.Wishlist
import com.example.mycapstone.response.Data
import com.example.mycapstone.response.DetailDestinationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {
    private val _DetailEvents = MutableLiveData<Data?>()
    val detailEvents: MutableLiveData<Data?> = _DetailEvents
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val mNoteRepository: DestinationRepository = DestinationRepository(application)

    suspend fun insert(favorite: Wishlist) {
        mNoteRepository.insert(favorite)
    }

    fun findUsername(username: String): LiveData<Wishlist> = mNoteRepository.findUsernameInDb(username)

    fun delete(favorite: Wishlist) {
        mNoteRepository.delete(favorite)
    }

    fun findEventActive(id: Int) {
        _isLoading.value = true
        val client = ApiClient.getApiService().getDetailDestinations(id)
        client.enqueue(object : Callback<DetailDestinationResponse> {
            override fun onResponse(
                call: Call<DetailDestinationResponse>,
                response: Response<DetailDestinationResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {

                    _DetailEvents.value = response.body()?.data
                } else {
                    Log.e("TAG", "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailDestinationResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("TAG", "onFailure: ${t.message.toString()}")
            }
        })
    }
}