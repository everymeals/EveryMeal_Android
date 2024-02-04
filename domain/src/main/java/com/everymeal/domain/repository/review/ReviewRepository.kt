package com.everymeal.domain.repository.review

import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.UserReview

interface ReviewRepository {
    suspend fun getReviewList(cursorIdx: Int, mealIdx: Int, pageSize: Int): Result<Review>
    suspend fun postReview(userReview: UserReview): Result<Boolean>
}
