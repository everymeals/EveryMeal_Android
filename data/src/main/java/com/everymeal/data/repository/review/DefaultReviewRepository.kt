package com.everymeal.data.repository.review

import com.everymeal.data.datasource.review.ReviewDataSource
import com.everymeal.data.model.review.toReviewRequest
import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.UserReview
import com.everymeal.domain.repository.review.ReviewRepository
import javax.inject.Inject

class DefaultReviewRepository @Inject constructor(
    private val reviewDataSource: ReviewDataSource
) : ReviewRepository {
    override suspend fun putReview(
        reviewIdx: Int,
        userReview: UserReview,
    ): Result<Boolean> = reviewDataSource.putReview(reviewIdx, userReview.toReviewRequest())


    override suspend fun deleteReview(reviewIdx: Int): Result<Unit> =
        reviewDataSource.deleteReview(reviewIdx)

    override suspend fun getReviewList(
        cursorIdx: Int,
        mealIdx: Int,
        pageSize: Int
    ): Result<Review> =
        reviewDataSource.getReviewList(cursorIdx, mealIdx, pageSize).map {
            it.toReview()
        }


    override suspend fun postReview(
        userReview: UserReview
    ): Result<Boolean> {
        return reviewDataSource.postReview(
            userReview.toReviewRequest()
        )
    }
}
