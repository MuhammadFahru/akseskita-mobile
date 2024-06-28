package com.upi.akseskita.core.data.repository

import com.upi.akseskita.core.data.source.remote.RemoteDataSource
import com.upi.akseskita.core.data.source.remote.network.ApiResponse
import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.domain.repository.IFacilityRepository
import com.upi.akseskita.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FacilityRepository(
    private val remoteDataSource: RemoteDataSource,
) : IFacilityRepository {
    override suspend fun getAllFacilities(): Flow<List<PlaceModel>> {
        return remoteDataSource.getAllFacilities().map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    DataMapper.mapResponseToDomain(response.data).take(5)
                }

                is ApiResponse.Error -> {
                    emptyList()
                }

                is ApiResponse.Empty -> {
                    emptyList()
                }
            }
        }
    }

    override suspend fun getTopRatedFacilities(): Flow<List<PlaceModel>> {
        return remoteDataSource.getAllFacilities().map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    DataMapper.mapResponseToDomain(response.data)
                        .sortedByDescending {
                            it.rating
                        }.take(5)
                }

                is ApiResponse.Error -> {
                    emptyList()
                }

                is ApiResponse.Empty -> {
                    emptyList()
                }
            }
        }
    }

    override suspend fun searchFacilities(query: String): Flow<List<PlaceModel>> {
        return remoteDataSource.searchFacilities(query).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    DataMapper.mapResponseToDomain(response.data)
                }

                is ApiResponse.Error -> {
                    emptyList()
                }

                is ApiResponse.Empty -> {
                    emptyList()
                }
            }
        }
    }

    override suspend fun searchFacilitiesByCategory(category: String): Flow<List<PlaceModel>> {
        return remoteDataSource.searchFacilitiesByCategory(category).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    DataMapper.mapResponseToDomain(response.data)
                }

                is ApiResponse.Error -> {
                    emptyList()
                }

                is ApiResponse.Empty -> {
                    emptyList()
                }
            }
        }
    }

    override suspend fun getDetailFacilities(id: Int): Flow<PlaceModel> {
        return remoteDataSource.getDetailFacilities(id)
            .map {
                when (it) {
                    is ApiResponse.Success -> {
                        DataMapper.detailResponseToDomain(it.data)
                    }

                    is ApiResponse.Error -> {
                        PlaceModel()
                    }

                    is ApiResponse.Empty -> {
                        PlaceModel()
                    }
                }
            }
    }
}