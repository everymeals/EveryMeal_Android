package com.everymeal.domain.repository.review

import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.UserReview

interface ReviewRepository {
    suspend fun putReview(reviewIdx: Int, userReview: UserReview): Result<Boolean>
    suspend fun deleteReview(reviewIdx: Int): Result<Unit>
    suspend fun getReviewList(cursorIdx: Int, mealIdx: Int, pageSize: Int): Result<Review>
    suspend fun postReview(userReview: UserReview): Result<Boolean>
}
