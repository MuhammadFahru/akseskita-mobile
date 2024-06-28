package com.upi.akseskita.core.domain.repository

import com.upi.akseskita.core.domain.model.PlaceModel
import kotlinx.coroutines.flow.Flow

interface IFacilityRepository {
    suspend fun getAllFacilities(): Flow<List<PlaceModel>>
    suspend fun getTopRatedFacilities(): Flow<List<PlaceModel>>
    suspend fun searchFacilities(query: String): Flow<List<PlaceModel>>
    suspend fun searchFacilitiesByCategory(category: String): Flow<List<PlaceModel>>
    suspend fun getDetailFacilities(id: Int): Flow<PlaceModel>
}