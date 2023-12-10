package com.everymeal.data.model.restaruant

import kotlinx.serialization.Serializable

@Serializable
data class GetUnivRestaurantResponse(
    val content: PagignRestaurantResponse
)

@Serializable
data class PagignRestaurantResponse(
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
    val empty: Boolean
)

@Serializable
data class RestaurantResponse(
    val idx: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val categoryDetail: String,
    val distance: Int,
    val grade: Int,
    val reviewCount: Int,
    val recommendedCount: Int,
    val images: List<String>?,
    val isLiked: Boolean
)

@Serializable
data class Pageable(
    val sort: Sort,
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val unpaged: Boolean
)

@Serializable
data class Sort(
    val empty: Boolean,
    val sorted: Boolean,
    val unsorted: Boolean
)