package com.everymeal.data.datasource.review

import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.StoreReviewRequest
import com.everymeal.data.model.unwrapRunCatching
import com.everymeal.data.service.review.StoreReviewApi
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val storeReviewApi: StoreReviewApi,
) : ReviewDataSource {
    override suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int
    ): Result<ReviewListResponse> {
        return unwrapRunCatching { storeReviewApi.getStoresReviews(
            offset = 0,
            limit = 3,
            order = order,
            group = group,
            grade = grade,
            campusIdx = campusIdx
        ) }
    }

    override suspend fun postReview(
        storeReviewRequest: StoreReviewRequest,
    ): Result<Boolean> = unwrapRunCatching { storeReviewApi.postStoreReview(storeReviewRequest) }
}
