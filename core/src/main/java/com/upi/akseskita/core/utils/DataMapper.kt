package com.upi.akseskita.core.utils

import android.util.Log
import com.upi.akseskita.core.data.source.remote.response.DetailFacilityResponse
import com.upi.akseskita.core.data.source.remote.response.FacilityResponse
import com.upi.akseskita.core.data.source.remote.response.ReviewsItem
import com.upi.akseskita.core.domain.model.DetailPlaceModel
import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.domain.model.ReviewModel

object DataMapper {
    fun mapResponseToDomain(input: FacilityResponse): List<PlaceModel> {
        val dataList = ArrayList<PlaceModel>()
        input.response?.map { facility ->
            val place = facility?.let { item ->
                PlaceModel(
                    id = item.id,
                    name = item.name,
                    category = item.category,
                    location = item.address,
                    rating = item.rating.toString(),
                    imageUrl = item.images?.map {
                        it?.image ?: ""
                    } ?: emptyList(),
                    tunaNetraFriendlyStatus = item.tunaNetraFriendlyStatus ?: 0,
                    tunaDaksaFriendlyStatus = item.tunaDaksaFriendlyStatus ?: 0
                )
            }

            if (place != null) {
                dataList.add(place)
            }
        }
        return dataList
    }

    fun detailResponseToDomain(input: DetailFacilityResponse): DetailPlaceModel {
        Log.i("DetailDataMapperInput", "${input.response}")
        val place = input.response.let {
            DetailPlaceModel(
                id = it?.id ?: 0,
                name = it?.name ?: "Data Kosong",
                category = it?.category ?: "",
                location = it?.address ?: "",
                rating = (it?.rating ?: 0f).toString(),
                imageUrl = it?.images?.map { item ->
                    item?.image ?: ""
                } ?: emptyList(),
                tunaNetraFriendlyStatus = it?.tunaNetraFriendlyStatus ?: 0,
                tunaDaksaFriendlyStatus = it?.tunaDaksaFriendlyStatus ?: 0,
                listReviews = reviewResponseToReviewDomain(it?.reviews ?: emptyList())
            )
        }
        Log.i("DetailDataMapperOutput", "$place")
        return place
    }

    private fun reviewResponseToReviewDomain(input: List<ReviewsItem?>): List<ReviewModel> {
        val dataList = ArrayList<ReviewModel>()
        input.map { item ->
            val review = ReviewModel(
                username = item?.user?.name ?: "",
                rating = item?.rating ?: 0f,
                review = item?.review ?: "",
                disabilityType = item?.user?.disabilityType ?: "",
            )

            dataList.add(review)
        }
        return dataList
    }
}