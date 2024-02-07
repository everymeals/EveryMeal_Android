package com.everymeal.domain.repository.review

import com.everymeal.domain.model.review.GetStoreReviewEntity
import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.UserReview

interface ReviewRepository {
    suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int,
    ): Result<GetStoreReviewEntity>
    suspend fun postReview(userReview: UserReview): Result<Boolean>
}
