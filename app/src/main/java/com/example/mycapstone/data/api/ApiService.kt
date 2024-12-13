package com.example.mycapstone.data.api





import com.example.mycapstone.response.DetailDestinationResponse
import com.example.mycapstone.response.ListDestinationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("destinations/")
    fun getDestinations(): Call<ListDestinationResponse>

    @GET("destinations/{id}")
    fun getDetailDestinations(
        @Path("id") id: Int
    ): Call<DetailDestinationResponse>
}