package com.everymeal.data.datasource.review

import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.StoreReviewRequest

interface ReviewDataSource {
    suspend fun getReviewList(
        cursorIdx: Int,
        mealIdx: Int,
        pageSize: Int,
    ): Result<ReviewListResponse>

    suspend fun postReview(storeReviewRequest: StoreReviewRequest): Result<Boolean>
}
