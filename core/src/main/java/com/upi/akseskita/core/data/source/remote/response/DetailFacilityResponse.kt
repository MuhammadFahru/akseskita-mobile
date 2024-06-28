package com.upi.akseskita.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailFacilityResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("response")
	val response: DetailFacilityResponse? = null,

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
	val tunaNetraFriendlyStatus: String? = null,

	@field:SerializedName("tuna_daksa_friendly_status")
	val tunaDaksaFriendlyStatus: String? = null,

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

data class User(

	@field:SerializedName("disability_type")
	val disabilityType: String? = null,

	@field:SerializedName("birthdate")
	val birthdate: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("deleted_by")
	val deletedBy: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: String? = null,

	@field:SerializedName("foto_profile")
	val fotoProfile: String? = null,

	@field:SerializedName("is_verified")
	val isVerified: Int? = null,

	@field:SerializedName("created_by")
	val createdBy: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("role_id")
	val roleId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("updated_by")
	val updatedBy: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("no_telp")
	val noTelp: String? = null,

	@field:SerializedName("verification_code")
	val verificationCode: Any? = null,

	@field:SerializedName("email")
	val email: String? = null
)