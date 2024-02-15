package com.everymeal.data.datasource.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.everymeal.data.datasource.restaurant.PAGING_SIZE
import com.everymeal.data.datasource.restaurant.STARTING_PAGE_INDEX
import com.everymeal.data.model.restaruant.RestaurantResponse
import com.everymeal.data.service.search.SearchService
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val searchService: SearchService,
    private val campusIdx: Int,
    private val keyword: String,
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
            val response = searchService.search(
                campusIdx = campusIdx,
                keyword = keyword,
                offset = position,
                limit = PAGING_SIZE
            )
            val restaurants = response.data?.content ?: emptyList()
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
