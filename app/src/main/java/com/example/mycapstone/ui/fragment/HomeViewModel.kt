package com.example.mycapstone.ui.fragment

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.data.api.ApiClient
import com.example.mycapstone.response.DataItem
import com.example.mycapstone.response.ListDestinationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _listDestination = MutableLiveData<List<DataItem>>()
    val listDestination: LiveData<List<DataItem>> = _listDestination
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getDestinations()
    }

    private fun getDestinations() {
        _isLoading.value = true
        val client = ApiClient.getApiService().getDestinations()
        client.enqueue(object : Callback<ListDestinationResponse> {
            override fun onResponse(
                call: Call<ListDestinationResponse>,
                response: Response<ListDestinationResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    // Set the response body to the LiveData
                    _listDestination.value = response.body()?.data!!
                } else {
                    Log.e("TAG", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListDestinationResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }
}
