package com.everymeal.data.datasource.review

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.everymeal.data.datasource.restaurant.PAGING_SIZE
import com.everymeal.data.datasource.restaurant.STARTING_PAGE_INDEX
import com.everymeal.data.model.restaruant.RestaurantResponse
import com.everymeal.data.model.review.ReviewResponse
import com.everymeal.data.service.restaurant.RestaurantApi
import com.everymeal.data.service.review.StoreReviewApi
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource (
    private val storeReviewApi: StoreReviewApi,
    private val order: String?,
    private val group: String?,
    private val grade: Int?,
    private val campusIdx: Int
) : PagingSource<Int, ReviewResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewResponse> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = storeReviewApi.getStoresReviews(
                campusIdx = campusIdx,
                order = order,
                group = group,
                grade = grade,
                offset = position,
                limit = PAGING_SIZE
            )
            val restaurants = response.data.content
            LoadResult.Page(
                data = restaurants,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (restaurants.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}