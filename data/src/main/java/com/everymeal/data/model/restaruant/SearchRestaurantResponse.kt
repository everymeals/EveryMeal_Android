package com.everymeal.data.model.restaruant

import com.everymeal.domain.model.restaurant.Restaurant
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
        val content: List<Content?>? = null,
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
        val sort: Sort? = null,
        @SerialName("totalElements")
        val totalElements: Int? = null,
        @SerialName("totalPages")
        val totalPages: Int? = null,
    ) {
        @Serializable
        data class Content(
            @SerialName("address")
            val address: String? = null,
            @SerialName("categoryDetail")
            val categoryDetail: String? = null,
            @SerialName("distance")
            val distance: Int? = null,
            @SerialName("grade")
            val grade: Int? = null,
            @SerialName("idx")
            val idx: Int? = null,
            @SerialName("images")
            val images: List<String?>? = null,
            @SerialName("isLiked")
            val isLiked: Boolean? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("phoneNumber")
            val phoneNumber: String? = null,
            @SerialName("recommendedCount")
            val recommendedCount: Int? = null,
            @SerialName("reviewCount")
            val reviewCount: Int? = null,
        )

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


fun SearchRestaurantResponse.toRestaurants(): List<Restaurant> {
    return this.data?.content?.mapNotNull { content ->
        content?.let {
            Restaurant(
                idx = it.idx ?: 0,
                name = it.name.orEmpty(),
                address = it.address.orEmpty(),
                phoneNumber = it.phoneNumber.orEmpty(),
                categoryDetail = it.categoryDetail.orEmpty(),
                distance = it.distance ?: 0,
                grade = it.grade?.toFloat()
                    ?: 0f,
                reviewCount = it.reviewCount ?: 0,
                recommendedCount = it.recommendedCount ?: 0,
                images = it.images?.filterNotNull(),
                isLiked = it.isLiked ?: false,
            )
        }
    } ?: emptyList()
}
