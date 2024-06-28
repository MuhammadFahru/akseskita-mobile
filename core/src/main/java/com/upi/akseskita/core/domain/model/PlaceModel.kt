package com.upi.akseskita.core.domain.model

data class PlaceModel(
    val id: Int? = 0,
    val name: String? = "",
    val category: String? = "",
    val location: String? = "",
    val rating: String? = "",
    val isFavorite: Boolean = false,
    val imageUrl: List<String> = emptyList(),
    val tunaNetraFriendlyStatus: Int = 0,
    val tunaDaksaFriendlyStatus: Int = 0,
)

data class DetailPlaceModel(
    val id: Int? = 0,
    val name: String? = "",
    val category: String? = "",
    val location: String? = "",
    val rating: String? = "",
    val isFavorite: Boolean = false,
    val imageUrl: List<String> = emptyList(),
    val tunaNetraFriendlyStatus: Int = 0,
    val tunaDaksaFriendlyStatus: Int = 0,
    val listReviews: List<ReviewModel>? = emptyList(),
)

data class ReviewModel(
    val username: String? = "",
    val review: String? = "",
    val rating: Float? = 0f,
    val disabilityType: String? = "",
)