package com.everymeal.data.datasource.search

import androidx.paging.PagingData
import com.everymeal.data.model.restaruant.RestaurantResponse
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {

    suspend fun searchRestraurant(
        campusIdx: Int,
        keyword: String,
    ): Flow<PagingData<RestaurantResponse>>


}
