package com.everymeal.data.model.restaruant

import com.everymeal.domain.model.restaurant.GetUnivRestaurantEntity
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import kotlinx.serialization.Serializable

@Serializable
data class GetUnivRestaurantResponse(
    val content: List<RestaurantResponse>,
    val pageable: Pageable,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val numberOfElements: Int,
    val first: Boolean,
    val empty: Boolean,
)

@Serializable
data class RestaurantResponse(
    val idx: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val categoryDetail: String,
    val distance: Int,
    val grade: Float,
    val reviewCount: Int,
    val recommendedCount: Int,
    val images: List<String>?,
    val isLiked: Boolean,
)

@Serializable
data class Pageable(
    val sort: Sort,
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val unpaged: Boolean,
)

@Serializable
data class Sort(
    val empty: Boolean,
    val sorted: Boolean,
    val unsorted: Boolean,
)

fun RestaurantResponse.toRestaurant(): RestaurantDataEntity {
    return RestaurantDataEntity(
        idx = this.idx,
        name = this.name,
        address = this.address,
        phoneNumber = this.phoneNumber,
        categoryDetail = this.categoryDetail,
        distance = this.distance,
        grade = this.grade,
        reviewCount = this.reviewCount,
        recommendedCount = this.recommendedCount,
        images = this.images,
        isLiked = this.isLiked,
    )
}


fun GetUnivRestaurantResponse.toGetUnivRestaurantEntity(): GetUnivRestaurantEntity {
    return GetUnivRestaurantEntity(
        data = this.content.map { it.toRestaurant() }
    )
}
