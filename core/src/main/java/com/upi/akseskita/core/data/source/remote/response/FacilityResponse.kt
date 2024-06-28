package com.upi.akseskita.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FacilityResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null
)

data class ResponseItem(

	@field:SerializedName("tuna_wicara_friendly_status")
	val tunaWicaraFriendlyStatus: Any? = null,

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("is_favorite")
	val isFavorite: Int? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("tuna_netra_friendly_status")
	val tunaNetraFriendlyStatus: Int? = null,

	@field:SerializedName("tuna_daksa_friendly_status")
	val tunaDaksaFriendlyStatus: Int? = null,

	@field:SerializedName("tuna_rungu_friendly_status")
	val tunaRunguFriendlyStatus: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)

data class Metadata(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errors")
	val errors: List<Any?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ImagesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("facility_id")
	val facilityId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ReviewsItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("review")
	val review: String? = null,

	@field:SerializedName("rating")
	val rating: Float? = null,

	@field:SerializedName("facility_id")
	val facilityId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,
)
