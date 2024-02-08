package com.everymeal.data.repository.review

import androidx.paging.PagingData
import androidx.paging.map
import com.everymeal.data.datasource.review.ReviewDataSource
import com.everymeal.data.model.restaruant.toRestaurant
import com.everymeal.data.model.review.toReviewRequest
import com.everymeal.data.model.review.toStoreReviewEntity
import com.everymeal.data.model.review.toStoreReviewEntityList
import com.everymeal.domain.model.review.GetStoreReviewEntity
import com.everymeal.domain.model.review.Review
import com.everymeal.domain.model.review.StoreReviewEntity
import com.everymeal.domain.model.review.UserReview
import com.everymeal.domain.repository.review.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultReviewRepository @Inject constructor(
    private val reviewDataSource: ReviewDataSource,
) : ReviewRepository {
    override suspend fun getPagingStoreReviews(
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int
    ): Flow<PagingData<StoreReviewEntity>> {
        return reviewDataSource.getPagingStoreReviews(
            order,
            group,
            grade,
            campusIdx
        ).map { pagingData ->
            pagingData.map { it.toStoreReviewEntity() }
        }
    }

    override suspend fun getStoreReviews(
        offset: Int,
        limit: Int,
        order: String?,
        group: String?,
        grade: Int?,
        campusIdx: Int,
    ): Result<GetStoreReviewEntity> {
        return reviewDataSource.getStoreReviews(
            offset,
            limit,
            order,
            group,
            grade,
            campusIdx
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
