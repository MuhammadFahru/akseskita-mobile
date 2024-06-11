package com.upi.akseskita.data.model

data class PlaceModel(
    val name: String,
    val category: String,
    val location: String,
    val rating: Float,
    val isFavorite: Boolean = false,
    val imageUrl: String,
)
