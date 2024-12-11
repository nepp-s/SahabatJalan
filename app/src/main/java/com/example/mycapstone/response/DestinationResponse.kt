package com.example.mycapstone.response

import com.google.gson.annotations.SerializedName

data class DestinationResponse(

	@field:SerializedName("category_id")
	val categoryId: CategoryId? = null,

	@field:SerializedName("worst_time_visit")
	val worstTimeVisit: String? = null,

	@field:SerializedName("date_created")
	val dateCreated: String? = null,

	@field:SerializedName("best_time_visit")
	val bestTimeVisit: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("guide")
	val guide: String? = null
)

data class CategoryId(

	@field:SerializedName("date_created")
	val dateCreated: String? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
