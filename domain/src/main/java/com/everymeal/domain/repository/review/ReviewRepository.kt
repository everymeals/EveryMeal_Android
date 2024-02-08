package com.everymeal.domain.repository.review

import androidx.paging.PagingData
import com.everymeal.domain.model.review.GetStoreReviewEntity
import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.StoreReviewEntity
import com.everymeal.domain.model.review.UserReview
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun getPagingStoreReviews(
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int,
    ): Flow<PagingData<StoreReviewEntity>>

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
