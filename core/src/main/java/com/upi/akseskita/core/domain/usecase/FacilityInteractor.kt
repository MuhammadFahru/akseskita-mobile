package com.upi.akseskita.core.domain.usecase

import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.domain.repository.IFacilityRepository
import kotlinx.coroutines.flow.Flow

class FacilityInteractor(private val facilityRepository: IFacilityRepository) : FacilityUseCase {
    override suspend fun getAllFacilities(): Flow<List<PlaceModel>> {
        return facilityRepository.getAllFacilities()
    }

    override suspend fun getTopRatedFacilities(): Flow<List<PlaceModel>> {
        return facilityRepository.getTopRatedFacilities()
    }

    override suspend fun searchFacilities(query: String): Flow<List<PlaceModel>> {
        return facilityRepository.searchFacilities(query)
    }

    override suspend fun searchFacilitiesByCategory(category: String): Flow<List<PlaceModel>> {
        return facilityRepository.searchFacilitiesByCategory(category)
    }

    override suspend fun getDetailFacilities(id: Int): Flow<PlaceModel> {
        return facilityRepository.getDetailFacilities(id)
    }
}