package com.everymeal.domain.usecase.review

import com.everymeal.domain.model.review.GetStoreReviewEntity
import com.everymeal.domain.repository.review.ReviewRepository
import javax.inject.Inject

class GetHomeReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?
    ) : Result<GetStoreReviewEntity> {
        return reviewRepository.getStoreReviews(offset, limit, order, group, grade)
    }
}