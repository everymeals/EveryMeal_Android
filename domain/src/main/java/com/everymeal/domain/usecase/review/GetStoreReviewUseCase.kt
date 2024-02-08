package com.everymeal.domain.usecase.review

import androidx.paging.PagingData
import com.everymeal.domain.model.review.StoreReviewEntity
import com.everymeal.domain.repository.review.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int
    ) : Flow<PagingData<StoreReviewEntity>> {
        return reviewRepository.getPagingStoreReviews(offset, limit, order, group, grade, campusIdx)
    }
}