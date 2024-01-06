package com.everymeal.data.datasource.restaurant

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.everymeal.data.model.restaruant.RestaurantResponse
import com.everymeal.data.service.restaurant.RestaurantApi
import retrofit2.HttpException
import java.io.IOException

class RestaurantPagingSource (
    private val restaurantApi: RestaurantApi,
    private val campusIdx: Int,
    private val order: String,
    private val group: String?,
    private val grade: String?
) : PagingSource<Int, RestaurantResponse>() {

    override fun getRefreshKey(state: PagingState<Int, RestaurantResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RestaurantResponse> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = restaurantApi.getUnivRestaurant(
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