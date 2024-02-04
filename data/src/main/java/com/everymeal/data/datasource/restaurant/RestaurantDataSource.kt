package com.everymeal.data.datasource.restaurant

import androidx.paging.PagingData
import com.everymeal.data.model.restaruant.GetUnivRestaurantResponse
import com.everymeal.data.model.restaruant.RestaurantResponse
import kotlinx.coroutines.flow.Flow

interface RestaurantDataSource {
    suspend fun getUnivRestaurant(
        campusIdx: Int,
        order: String,
        group: String? = null,
        grade: String? = null,
    ): Flow<PagingData<RestaurantResponse>>

    suspend fun getRestaurantDetail(index: Int): Result<RestaurantResponse>

    suspend fun getHomeRestaurant(
        campusIdx: Int,
        order: String,
        group: String? = null,
        grade: String? = null,
    ): Result<GetUnivRestaurantResponse>
}