package com.everymeal.data.datasource.review

import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.ReviewRequest
import com.everymeal.data.model.unwrapRunCatching
import com.everymeal.data.service.review.ReviewApi
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val reviewApi: ReviewApi
) : ReviewDataSource {
    override suspend fun putReview(
        reviewIdx: Int,
        reviewRequest: ReviewRequest
    ): Result<Boolean> = unwrapRunCatching { reviewApi.putReview(reviewIdx, reviewRequest) }


    override suspend fun deleteReview(reviewIdx: Int): Result<Unit> = unwrapRunCatching {
        reviewApi.deleteReview(reviewIdx)
    }

    override suspend fun getReviewList(
        cursorIdx: Int,
        mealIdx: Int,
        pageSize: Int
    ): Result<ReviewListResponse> =
        unwrapRunCatching {
            reviewApi.getReviewList(
                cursorIdx,
                mealIdx,
                pageSize
            )
        }


    override suspend fun postReview(
        reviewRequest: ReviewRequest
    ): Result<Boolean> = unwrapRunCatching { reviewApi.postReview(reviewRequest) }
}
