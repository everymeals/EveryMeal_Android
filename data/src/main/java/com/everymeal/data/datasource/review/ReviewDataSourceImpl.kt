package com.everymeal.data.datasource.review

import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.StoreReviewRequest
import com.everymeal.data.model.unwrapRunCatching
import com.everymeal.data.service.review.StoreReviewApi
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val storeReviewApi: StoreReviewApi,
) : ReviewDataSource {

    override suspend fun getReviewList(
        cursorIdx: Int,
        mealIdx: Int,
        pageSize: Int,
    ): Result<ReviewListResponse> =
        unwrapRunCatching {
            storeReviewApi.getStoreReviewsWithId(
                cursorIdx,
                mealIdx,
                pageSize,
            )
        }

    override suspend fun postReview(
        storeReviewRequest: StoreReviewRequest,
    ): Result<Boolean> = unwrapRunCatching { storeReviewApi.postStoreReview(storeReviewRequest) }
}
