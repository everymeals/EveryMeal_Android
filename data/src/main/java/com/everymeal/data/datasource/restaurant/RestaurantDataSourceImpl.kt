package com.everymeal.data.datasource.restaurant

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.everymeal.data.model.restaruant.GetUnivRestaurantResponse
import com.everymeal.data.model.restaruant.RestaurantResponse
import com.everymeal.data.model.unwrapData
import com.everymeal.data.service.restaurant.RestaurantApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val PAGING_SIZE = 20
const val STARTING_PAGE_INDEX = 0

class RestaurantDataSourceImpl @Inject constructor(
    private val restaurantApi: RestaurantApi
) : RestaurantDataSource {
    override suspend fun getUnivRestaurant(
        campusIdx: Int,
        order: String,
        group: String?,
        grade: String?
    ) : Flow<PagingData<RestaurantResponse>> {
        val pagingSourceFactory = { RestaurantPagingSource(restaurantApi, campusIdx, order, group, grade) }
        return Pager(
            config = PagingConfig(
                initialLoadSize = PAGING_SIZE,
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getRestaurantDetail(index: Int): Result<RestaurantResponse> {
        return runCatching { restaurantApi.getRestaurantDetail(index) }.unwrapData()
    }

    override suspend fun getHomeRestaurant(
        campusIdx: Int,
        order: String,
        group: String?,
        grade: String?
    ): Result<GetUnivRestaurantResponse> {
        return runCatching { restaurantApi.getUnivRestaurant(
            campusIdx = campusIdx,
            order = order,
            group = group,
            grade = grade,
            offset = 0,
            limit = 3)
        }.unwrapData()
    }
}