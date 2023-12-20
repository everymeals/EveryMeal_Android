package com.everymeal.domain.usecase.review

import com.everymeal.domain.model.review.UserReview
import com.everymeal.domain.repository.review.ReviewRepository
import javax.inject.Inject

class PostReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(userReview: UserReview) {
        reviewRepository.postReview(userReview)
    }
}
