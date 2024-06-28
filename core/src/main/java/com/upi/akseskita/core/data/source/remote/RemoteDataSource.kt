package com.upi.akseskita.core.data.source.remote

import android.util.Log
import com.upi.akseskita.core.data.source.remote.network.ApiResponse
import com.upi.akseskita.core.data.source.remote.network.ApiService
import com.upi.akseskita.core.data.source.remote.response.DetailFacilityResponse
import com.upi.akseskita.core.data.source.remote.response.FacilityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllFacilities(): Flow<ApiResponse<FacilityResponse>> {
        return flow {
            try {
                val response = apiService.getAllFacilities()

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchFacilities(query: String): Flow<ApiResponse<FacilityResponse>> {
        return flow {
            try {
                val response = apiService.searchFacilities(query)

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchFacilitiesByCategory(category: String): Flow<ApiResponse<FacilityResponse>> {
        return flow {
            try {
                val response = apiService.searchFacilitiesByCategory(category)

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailFacilities(id: Int): Flow<ApiResponse<DetailFacilityResponse>> {
        return flow {
            try {
                val response = apiService.getDetailFacilities(id)

                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}