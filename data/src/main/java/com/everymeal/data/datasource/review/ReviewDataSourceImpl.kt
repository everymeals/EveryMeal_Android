package com.everymeal.data.datasource.review

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.everymeal.data.datasource.restaurant.PAGING_SIZE
import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.ReviewResponse
import com.everymeal.data.model.review.StoreReviewRequest
import com.everymeal.data.model.unwrapRunCatching
import com.everymeal.data.service.review.StoreReviewApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor(
    private val storeReviewApi: StoreReviewApi,
) : ReviewDataSource {
    override suspend fun getPagingStoreReviews(
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int
    ): Flow<PagingData<ReviewResponse>> {
        val pagingSourceFactory = { ReviewPagingSource(storeReviewApi, order, group, grade, campusIdx) }
        return Pager(
            config = PagingConfig(
                initialLoadSize = PAGING_SIZE,
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int
    ): Result<ReviewListResponse> {
        return unwrapRunCatching { storeReviewApi.getStoresReviews(
            offset = 0,
            limit = 3,
            order = order,
            group = group,
            grade = grade,
            campusIdx = campusIdx
        ) }
    }

    override suspend fun postReview(
        storeReviewRequest: StoreReviewRequest,
    ): Result<Boolean> = unwrapRunCatching { storeReviewApi.postStoreReview(storeReviewRequest) }
}
