package com.everymeal.data.model.review


import com.everymeal.domain.model.review.GetStoreReviewEntity
import com.everymeal.domain.model.review.StoreReviewEntity
import kotlinx.serialization.Serializable

@Serializable
data class ReviewListResponse(
    val content: List<ReviewResponse>,
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
data class ReviewResponse(
    val reviewIdx: Int,
    val content: String,
    val grade: Int,
    val createdAt: String,
    val formattedCreatedAt: String,
    val nickName: String,
    val profileImageUrl: String,
    val reviewMarksCnt: Int,
    val images: List<String>?,
    val storeIdx: Int,
    val storeName: String,
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

fun ReviewResponse.toStoreReviewEntity(): StoreReviewEntity {
    return StoreReviewEntity(
        reviewIdx = this.reviewIdx,
        content = this.content,
        grade = this.grade,
        createdAt = this.createdAt,
        formattedCreatedAt = this.formattedCreatedAt,
        nickName = this.nickName,
        profileImageUrl = this.profileImageUrl,
        reviewMarksCnt = this.reviewMarksCnt,
        images = this.images,
        storeIdx = this.storeIdx,
        storeName = this.storeName,
    )
}

fun ReviewListResponse.toStoreReviewEntityList(): GetStoreReviewEntity {
    return GetStoreReviewEntity(
        data = this.content.map { it.toStoreReviewEntity() }
    )
}
