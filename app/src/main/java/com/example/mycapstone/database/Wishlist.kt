package com.example.mycapstone.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination")
data class Wishlist (
    @PrimaryKey
    var categoryId: Int,
    var worsTimeVisit: String,
    var bestTimeVisit: String,
    var name: String,
    var description: String,
    var location: String,
    var guide: String
)

