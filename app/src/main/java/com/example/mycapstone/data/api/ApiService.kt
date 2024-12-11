package com.example.mycapstone.data.api

import com.example.mycapstone.database.Wishlist
import retrofit2.http.GET


interface ApiService {
    @GET("/destinations/")
    suspend fun getDestinations(): List<Wishlist>
}