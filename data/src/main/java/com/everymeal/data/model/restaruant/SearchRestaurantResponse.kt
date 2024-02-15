package com.everymeal.data.model.restaruant

import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRestaurantResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("localDateTime")
    val localDateTime: String? = null,
    @SerialName("message")
    val message: String? = null,
) {
    @Serializable
    data class Data(
        @SerialName("content")
        val content: List<RestaurantResponse>? = null,
        @SerialName("empty")
        val empty: Boolean? = null,
        @SerialName("first")
        val first: Boolean? = null,
        @SerialName("last")
        val last: Boolean? = null,
        @SerialName("number")
        val number: Int? = null,
        @SerialName("numberOfElements")
        val numberOfElements: Int? = null,
        @SerialName("pageable")
        val pageable: Pageable? = null,
        @SerialName("size")
        val size: Int? = null,
        @SerialName("sort")
        val sort: Pageable.Sort? = null,
        @SerialName("totalElements")
        val totalElements: Int? = null,
        @SerialName("totalPages")
        val totalPages: Int? = null,
    ) {

        @Serializable
        data class Pageable(
            @SerialName("offset")
            val offset: Int? = null,
            @SerialName("pageNumber")
            val pageNumber: Int? = null,
            @SerialName("pageSize")
            val pageSize: Int? = null,
            @SerialName("paged")
            val paged: Boolean? = null,
            @SerialName("sort")
            val sort: Sort? = null,
            @SerialName("unpaged")
            val unpaged: Boolean? = null,
        ) {
            @Serializable
            data class Sort(
                @SerialName("empty")
                val empty: Boolean? = null,
                @SerialName("sorted")
                val sorted: Boolean? = null,
                @SerialName("unsorted")
                val unsorted: Boolean? = null,
            )
        }
    }
}


fun SearchRestaurantResponse.toRestaurants(): List<RestaurantDataEntity?> {
    return this.data?.content?.map { content ->
        content.let {
            RestaurantDataEntity(
                idx = it.idx,
                name = it.name,
                address = it.address,
                phoneNumber = it.phoneNumber,
                categoryDetail = it.categoryDetail,
                distance = it.distance,
                grade = it.grade,
                reviewCount = it.reviewCount,
                recommendedCount = it.recommendedCount,
                images = it.images,
                isLiked = it.isLiked,
            )
        }
    } ?: emptyList()
}
