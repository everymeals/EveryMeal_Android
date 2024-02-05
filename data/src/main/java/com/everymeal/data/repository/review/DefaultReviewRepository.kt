package com.everymeal.data.repository.review

import com.everymeal.data.datasource.review.ReviewDataSource
import com.everymeal.data.model.review.toReviewRequest
import com.everymeal.data.model.review.toStoreReviewEntityList
import com.everymeal.domain.model.review.GetStoreReviewEntity
import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.UserReview
import com.everymeal.domain.repository.review.ReviewRepository
import javax.inject.Inject

class DefaultReviewRepository @Inject constructor(
    private val reviewDataSource: ReviewDataSource,
) : ReviewRepository {
    override suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?
    ): Result<GetStoreReviewEntity> {
        return reviewDataSource.getStoreReviews(
            offset,
            limit,
            order,
            group,
            grade,
        ).map { it.toStoreReviewEntityList() }
    }

    override suspend fun postReview(
        userReview: UserReview,
    ): Result<Boolean> {
        return reviewDataSource.postReview(
            userReview.toReviewRequest(),
        )
    }
}
