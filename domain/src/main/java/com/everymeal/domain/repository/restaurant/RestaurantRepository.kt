package com.everymeal.domain.repository.restaurant

import androidx.paging.PagingData
import com.everymeal.domain.model.restaurant.GetUnivRestaurantEntity
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getUnivRestaurant(
        campusIdx: Int,
        order: String,
        group: String? = null,
        grade: String? = null,
    ): Flow<PagingData<RestaurantDataEntity>>

    suspend fun getRestaurantDetail(
        index: Int
    ): Result<RestaurantDataEntity>

    suspend fun getHomeRestaurant(
        campusIdx: Int,
        order: String,
        group: String? = null,
        grade: String? = null,
    ): Result<GetUnivRestaurantEntity>
}
