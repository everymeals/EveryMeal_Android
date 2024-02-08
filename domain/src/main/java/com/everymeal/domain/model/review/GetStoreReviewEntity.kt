package com.everymeal.domain.model.review

data class GetStoreReviewEntity(
    val data: List<StoreReviewEntity>
)

data class StoreReviewEntity(
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