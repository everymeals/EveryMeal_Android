package com.everymeal.data.datasource.review

import androidx.paging.PagingData
import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.ReviewResponse
import com.everymeal.data.model.review.StoreReviewRequest
import com.everymeal.domain.model.review.GetStoreReviewEntity
import kotlinx.coroutines.flow.Flow

interface ReviewDataSource {

    suspend fun getPagingStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int,
    ): Flow<PagingData<ReviewResponse>>

    suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int,
    ): Result<ReviewListResponse>

    suspend fun postReview(storeReviewRequest: StoreReviewRequest): Result<Boolean>
}
