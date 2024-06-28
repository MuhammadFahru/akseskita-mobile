package com.upi.akseskita.core.data.source.remote.network

import com.upi.akseskita.core.data.source.remote.response.DetailFacilityResponse
import com.upi.akseskita.core.data.source.remote.response.FacilityResponse
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("facilities/{id}")
    suspend fun getDetailFacilities(
        @Path("id") id: Int,
    ): DetailFacilityResponse

    @POST("facilities")
    suspend fun getAllFacilities(): FacilityResponse

    @POST("facilities")
    suspend fun searchFacilities(
        @Query("search") search: String
    ): FacilityResponse

    @POST("facilities")
    suspend fun searchFacilitiesByCategory(
        @Query("category") category: String
    ): FacilityResponse
}