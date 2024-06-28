package com.upi.akseskita.core.domain.model

data class PlaceModel(
    val name: String? = "",
    val category: String? = "",
    val location: String? = "",
    val rating: String? = "",
    val isFavorite: Boolean = false,
    val imageUrl: List<String> = emptyList(),
    val tunaNetraFriendlyStatus: String = "",
    val tunaDaksaFriendlyStatus: String = "",
)
