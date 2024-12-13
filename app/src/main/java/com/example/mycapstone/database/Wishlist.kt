package com.example.mycapstone.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination")
data class Wishlist (
    @PrimaryKey
    var id: Int,
    var name: String,
    var location: String,
    var imageLocation: String,
)

