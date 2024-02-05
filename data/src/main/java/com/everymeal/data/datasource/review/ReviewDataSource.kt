package com.everymeal.data.datasource.review

import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.StoreReviewRequest

interface ReviewDataSource {

    suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?
    ): Result<ReviewListResponse>

    suspend fun postReview(storeReviewRequest: StoreReviewRequest): Result<Boolean>
}
