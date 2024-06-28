package com.upi.akseskita.core.utils

import com.upi.akseskita.core.data.source.remote.response.DetailFacilityResponse
import com.upi.akseskita.core.data.source.remote.response.FacilityResponse
import com.upi.akseskita.core.domain.model.PlaceModel

object DataMapper {
    fun mapResponseToDomain(input: FacilityResponse): List<PlaceModel> {
        val dataList = ArrayList<PlaceModel>()
        input.response?.map { facility ->
            val place = facility?.let { item ->
                PlaceModel(
                    name = item.name,
                    category = item.category,
                    location = item.address,
                    rating = item.rating.toString(),
                    imageUrl = item.images?.map {
                        it?.image ?: ""
                    } ?: emptyList(),
                    tunaNetraFriendlyStatus = item.tunaNetraFriendlyStatus ?: "",
                    tunaDaksaFriendlyStatus = item.tunaDaksaFriendlyStatus ?: ""
                )
            }

            if (place != null) {
                dataList.add(place)
            }
        }
        return dataList
    }

    fun detailResponseToDomain(input: DetailFacilityResponse): PlaceModel {
        return PlaceModel(
            name = input.name,
            category = input.category,
            location = input.address,
            rating = input.rating.toString(),
            imageUrl = input.images?.map {
                it?.image ?: ""
            } ?: emptyList(),
            tunaNetraFriendlyStatus = input.tunaNetraFriendlyStatus ?: "",
            tunaDaksaFriendlyStatus = input.tunaDaksaFriendlyStatus ?: ""
        )
    }
}