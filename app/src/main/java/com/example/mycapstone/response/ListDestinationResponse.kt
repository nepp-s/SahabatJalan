package com.example.mycapstone.response

import com.google.gson.annotations.SerializedName

data class ListDestinationResponse(

	@field:SerializedName("data")
	val data: List<DataItem>?
)

data class DataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
