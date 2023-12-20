package com.everymeal.data.datasource.review

import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.ReviewRequest

interface ReviewDataSource {
    suspend fun putReview(reviewIdx: Int, reviewRequest: ReviewRequest): Result<Boolean>

    suspend fun deleteReview(reviewIdx: Int): Result<Unit>
    suspend fun getReviewList(
        cursorIdx: Int,
        mealIdx: Int,
        pageSize: Int
    ): Result<ReviewListResponse>

    suspend fun postReview(reviewRequest: ReviewRequest): Result<Boolean>

}
