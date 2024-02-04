package com.everymeal.domain.repository.restaurant

import androidx.paging.PagingData
import com.everymeal.domain.model.restaurant.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getUnivRestaurant(
        campusIdx: Int,
        order: String,
        group: String? = null,
        grade: String? = null,
    ): Flow<PagingData<Restaurant>>
}
