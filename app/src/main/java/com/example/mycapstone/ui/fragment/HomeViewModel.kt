package com.example.mycapstone.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.data.api.ApiClient
import com.example.mycapstone.response.CategoryId
import com.example.mycapstone.response.DestinationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _categoryId = MutableLiveData<List<CategoryId>?>()
    val categoryId: LiveData<List<CategoryId>> = _categoryId
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getDestinations()
    }

    private fun getDestinations() {
        _isLoading.value = true
        val client = ApiClient.apiService().getDestinations()
        client.enqueue(object : Callback<DestinationResponse> {
            override fun onResponse(
                call: Call<DestinationResponse>,
                response: Response<DestinationResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {

                    _categoryId.value = response.body()?.categoryId
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