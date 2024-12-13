package com.example.mycapstone.response

import com.google.gson.annotations.SerializedName

data class DetailDestinationResponse(

	@field:SerializedName("data")
	val data: Data? = null
)

data class CategoryIdItem(

	@field:SerializedName("date_created")
	val dateCreated: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Data(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("category_id")
	val categoryId: List<CategoryIdItem?>? = null,

	@field:SerializedName("worst_time_visit")
	val worstTimeVisit: String? = null,

	@field:SerializedName("date_created")
	val dateCreated: String? = null,

	@field:SerializedName("best_time_visit")
	val bestTimeVisit: String? = null,

	@field:SerializedName("previews")
	val previews: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("guide")
	val guide: String? = null
)
